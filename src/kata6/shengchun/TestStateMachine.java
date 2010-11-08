package stateMachine;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Nov 7, 2010
 * Time: 10:59:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestStateMachine {
    @Test
    public void test1 ()
    {
        StateMachine fsm = new StateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail",  "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0");
        assertEquals("pass", fsm.execute("00110"));
        assertEquals("fail", fsm.execute("00111"));    
        assertEquals("fail", fsm.execute("001110000110011"));
        assertEquals("fail", null,fsm.execute("0011100001130011"));
    }
}
