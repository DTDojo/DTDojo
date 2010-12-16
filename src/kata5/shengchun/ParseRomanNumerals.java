package parseRomanNumerals;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 28, 2010
 * Time: 1:45:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class ParseRomanNumerals {
    int _input;
    Map<Integer,String> onesConvertMap = new HashMap<Integer,String>(9){{
        put(0,"");
        put(1,"I");
        put(2,"II");
        put(3,"III");
        put(4,"IV");
        put(5,"V");
        put(6,"VI");
        put(7,"VII");
        put(8,"VIII");
        put(9,"IX");

    }};
    Map<Integer,String> tensConvertMap = new HashMap<Integer,String>(9){{
        put(0,"");
        put(1,"X");
        put(2,"XX");
        put(3,"XXX");
        put(4,"XL");
        put(5,"L");
        put(6,"LX");
        put(7,"LXX");
        put(8,"LXXX");
        put(9,"XC");
    }};
    Map<Integer,String> hundredsConvertMap = new HashMap<Integer,String>(){{
        put(0,"");
        put(1,"C");
        put(2,"CC");
        put(3,"CCC");
        put(4,"CD");
        put(5,"D");
        put(6,"DC");
        put(7,"DCC");
        put(8,"DCCC");
        put(9,"CM");
    }};


    public ParseRomanNumerals(int input)
    {
         _input = input;
    }
    public String parse()
    {
        String returnValue = "";
        String prefix = "";
        String suffix = "";
        while(_input/1000 > 0 )
        {
            int current = _input/1000;
            int toBeParsed = _input%1000;

            returnValue = prefix + parseUnder1000(toBeParsed) + suffix +  returnValue;
            _input = current;
            prefix += "(";
            suffix += ")";
        }
        returnValue = prefix + parseUnder1000(_input) + suffix +  returnValue;        
        returnValue = returnValue.replaceAll("\\(I\\)", "M");
        returnValue = returnValue.replaceAll("\\(II\\)", "MM");
        returnValue = returnValue.replaceAll("\\(III\\)", "MMM");
        return  returnValue;
    }

    private String parseUnder1000(int toBeParsed) {
        if(toBeParsed <= 0)
        {
            return "";
        }
        int ones = toBeParsed%10;
        int tens = (toBeParsed/10)%10;
        int hundreds = (toBeParsed/100)%10;
        return hundredsConvertMap.get(hundreds) + tensConvertMap.get(tens) + onesConvertMap.get(ones);
    }


    public static void main(String[] args)
    {
        ParseRomanNumerals pr = null;
        try {
            pr = new ParseRomanNumerals(Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            System.out.println("please input a valid integernumber"+ e.getStackTrace());  
        }
        System.out.println(pr.parse()) ;
    }
}
