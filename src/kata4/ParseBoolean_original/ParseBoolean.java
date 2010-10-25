package parseBoolean;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 21, 2010
 * Time: 10:39:47 PM
 */
public class ParseBoolean {
    private String _input ;

    public ParseBoolean(String input)
    {
        _input = input;
    }


    public int parse()
    {        
        String expression = "  if(" + _input + "){" +
            "returnValue = VALUEA;" +
        "}" +
                " else { returnValue = VALUEB; }";
        expression = expression.replaceAll("0", " false " );
        expression = expression.replaceAll("1", " true " );
        expression = expression.replaceAll("\\*", " && " );
        expression = expression.replaceAll("\\+", " || " );
        expression = expression.replace("VALUEA", " \"1\" " );
        expression = expression.replace("VALUEB", " \"0\" " );

        System.out.println(expression);
        ScriptEngineManager manager
            = new ScriptEngineManager();
        ScriptEngine engine
            = manager.getEngineByName("js");
        Object result = null;
        try {

            result = engine.eval(expression);
        }
        catch (ScriptException e)
        {

        }
        return result == null ? 0 :Integer.parseInt(result.toString());
    }
}
