package cyclicWordsKata;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Oct 17, 2010
 * Time: 8:22:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCyclicWordsKata {
    @Test
    public void testOneCyclicPair() {
        String[] input = {"abc","acb", "cab"};
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();
        assertEquals("Result", 2, results.size());
	}

    @Test
    public void testThreeCyclicPair() {
        String[] input = {"abc", "bca", "cde"};
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();
        for(int i = 0;i<results.size(); i++){
           System.out.println("["+ tester.joinStringArray(results.get(i), ",")+"]");

        }
        assertEquals("Result", 2, results.size());
	}

    @Test
    public void testThreePairs() {
        String[] input = {"abc","cba","bca", "dfe", "abcd","dbac","bcda","cdab","dabc","abc"};
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();
        for(int i = 0;i<results.size(); i++){
           System.out.println("["+ tester.joinStringArray(results.get(i), ",")+"]");

        }
        assertEquals("Result", 5, results.size());
	}

    @Test
    public void testDuplicateElements() {
        String[] input = {"abc","abc"};
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();
        for(int i = 0;i<results.size(); i++){
            if(results != null){
               System.out.println("["+ tester.joinStringArray(results.get(i), ",")+"]");
            }
        }
        assertEquals("Result", 1, results.size());
        assertEquals("results", "abc", (results.get(0)).get(0));
        assertEquals("results", 1, (results.get(0)).size());
    }

    @Test
    public void testBlank() {
        String[] input = {""};
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();
        for(int i = 0;i<results.size(); i++){
           System.out.println("["+ tester.joinStringArray(results.get(i), ",")+"]");

        }
        assertEquals("Result", 0, results.size());
    }

    @Test
    public void testNull() {
        String[] input = null;
        CyclicWordsKata tester = new CyclicWordsKata(input);
        List<List<String>> results = tester.process();

        assertNull("results", results);
    }
}
