package parseBoolean;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 24, 2010
 * Time: 7:30:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParseBooleanOriginal {

    private String _input;
    public ParseBooleanOriginal(String input){
        _input = input;
    }
    Pattern p1 = Pattern.compile("^1\\+");
    Pattern p2 = Pattern.compile("\\+1$");
    Pattern p3 = Pattern.compile("\\+1\\+");
    Pattern p4 = Pattern.compile("\\(([01\\+\\*]*)\\)");
    Pattern p5 = Pattern.compile("(1\\*)*0(\\*)?(0\\*)*[01]?");
    Pattern p = Pattern.compile("^\\((.+)\\)$");

    public int process()
    {
        _input = _input.replaceAll(" ", "");        
        
        _input = _input.replaceAll("!1", "0");
        _input = _input.replaceAll("!0", "1");

        Matcher m = p.matcher(_input);
        if(m.find())
        {
            _input = _input.substring(1,_input.length()-1);            
        }

        
        Matcher m1 = p1.matcher(_input);
        Matcher m2 = p2.matcher(_input);

        //start with 1+ or end with +1
        //short circuit to 1
        if(m1.find() || m2.find())
        {
            return 1;
        }
        Matcher m4 = p4.matcher(_input);
        String foundInnerLogic;
        String parsed;
        while (m4.find())
        {
            foundInnerLogic = m4.group();
            if(foundInnerLogic != null){
                parsed = parseInnerLogic(foundInnerLogic);
                String matched = foundInnerLogic;
                /*
                String matched =  foundInnerLogic.replaceAll("\\*","\\\\\\*");
                matched =  matched.replaceAll("\\(","\\\\\\(");
                matched =  matched.replaceAll("\\)","\\\\\\)");
                */
                _input = _input.replace(matched, parsed);
                _input = _input.replace("!0", "1");
                _input = _input.replace("!1", "0");
                m4 = p4.matcher(_input);
            }
        }
        _input = parseInnerLogic(_input);
        return Integer.parseInt(_input);
    }

    private String parseInnerLogic(String foundInnerLogic) {
        if(foundInnerLogic.length() == 1 ){
            return foundInnerLogic;
        }
        foundInnerLogic = foundInnerLogic.replace("(", "");
        foundInnerLogic = foundInnerLogic.replace(")", "");
        Matcher m1 = p1.matcher(foundInnerLogic);
        Matcher m2 = p2.matcher(foundInnerLogic);
        Matcher m3 = p3.matcher(foundInnerLogic);
        if(m1.find() || m2.find() || m3.find())
        {
            return "1";
        }
        foundInnerLogic = foundInnerLogic.replaceAll("\\*1", "");
        Matcher m5 = p5.matcher(foundInnerLogic);
        while(m5.find())
        {
            if(m5.group() != null )
            {
                String m = m5.group().replaceAll("\\*", "\\\\\\*");                
                foundInnerLogic = foundInnerLogic.replaceAll(m, "0");
            }

        }
        m1 = p1.matcher(foundInnerLogic);
        m2 = p2.matcher(foundInnerLogic);
        m3 = p3.matcher(foundInnerLogic);
        if(m1.find() || m2.find() || m3.find())
        {
            return "1";
        }
        return "0";

    }
    public static void main(String[] args)
    {
        String input = args[0];
        if(input != null)
        {
            ParseBooleanOriginal pb = new ParseBooleanOriginal(input);
            int result = pb.process();
            System.out.println("result: " + result);
        }
    }

}
