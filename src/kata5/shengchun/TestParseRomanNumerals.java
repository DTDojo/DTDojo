package parseRomanNumerals;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 29, 2010
 * Time: 9:11:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestParseRomanNumerals {
    @Test
    public void test1() {
            int input = 1879;
            ParseRomanNumerals tester = new ParseRomanNumerals(input);
            String testResults = tester.parse();
            assertEquals("Result","MDCCCLXXIX", testResults );
        }
    @Test
    public void test2() {
            int input = 1;
            ParseRomanNumerals tester = new ParseRomanNumerals(input);
            String testResults = tester.parse();
            assertEquals("Result","I", testResults );
        }
    @Test
    public void test3() {
            int input = 2010;
            ParseRomanNumerals tester = new ParseRomanNumerals(input);
            String testResults = tester.parse();
            assertEquals("Result","MMX", testResults );
        }
    @Test
    public void test4() {
            int input = 47;
            ParseRomanNumerals tester = new ParseRomanNumerals(input);
            String testResults = tester.parse();
            assertEquals("Result","XLVII", testResults );
    }
}
