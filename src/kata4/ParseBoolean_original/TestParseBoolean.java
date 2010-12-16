package parseBoolean;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 21, 2010
 * Time: 10:57:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestParseBoolean {
    @Test
    public void test1() {
        String input = "(1+0*1)";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
	}
    @Test
    public void test2() {
        String input = "(!0)";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
	}
    @Test
    public void test3() {
        String input = "0";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",0, testResults );
	}
    @Test
    public void test4() {
        String input = "0 * 1 * 1";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",0, testResults );
	}
    @Test
    public void test5() {
        String input = "(1+0)";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
	}
    @Test
    public void test6() {
        String input = "!0 * 0 + 1";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
    }
    @Test
    public void test7() {
        String input = "(0+1) * (1+0)";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
    }

    @Test
    public void test8() {
        String input = "((0+0+(1+!1+!(0 + !(0*1+0*1*1+1*0)+1)+0*1)+1*0*0)+0*1)";
        ParseBoolean tester = new ParseBoolean(input);
        int testResults = tester.parse();
        assertEquals("Result",1, testResults );
    }

}
