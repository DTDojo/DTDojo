package stateMachine;

import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Nov 6, 2010
 * Time: 8:54:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class StateMachine {
    private String _symbols;
    private String _states ;
    private String _transitions;
    Map<String, String> states = new HashMap<String,String>();
    Map<String, String> endStates = new HashMap<String, String>();
    String entryState = null;


    public StateMachine(String symbols, String statesIn, String transitions){
        _symbols = symbols;
        _states = statesIn;
        _transitions = transitions;        
        setupMachine();
    }
    private void setupMachine()
    {

        String[] stateList = _states.split(",");

        for(int i = 0; i < stateList.length;i++)
        {
             String[] pair = stateList[i].split(":");
             states.put(pair[0], pair[1]) ;

            if (i == 0) {
                entryState = pair[0];
            }
        }
        String[] transitionLists = _transitions.split(",");

        for(int i = 0; i <transitionLists.length; i++)
        {
            String[] elements = transitionLists[i].split(":");
            String startState = elements[0];
            String endState = elements[1];
            String event = elements[2];
            endStates.put(startState+"_"+event, endState);
        }
    }
    public String execute(String input)
    {
        String startState =  entryState;
        for(int i = 0; i < input.length();i++){
            char event = input.charAt(i) ;
            //invalid input
            if(_symbols.indexOf(event) < 0)
            {
                  return null;
            }
            String key = startState + "_" + event;
            //System.out.println("State " + key);
            if (endStates.containsKey(key)) {
                startState = endStates.get(key);
            }
        }
        if (states.containsKey(startState)) {
            return states.get(startState);
        }
        return null;
    }
    public static void main(String[] args)
    {
        StateMachine fsm = new StateMachine(args[0],args[1], args[2]);
        System.out.println("return value " + fsm.execute(args[3]));
    }
}
