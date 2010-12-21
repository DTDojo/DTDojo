

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Map;

/**
 * Test cases for SecretSantaFinder
 * User: sye
 * Date: Nov 14, 2010
 * Time: 11:38:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSecretSantaFinder {
    @Test
    public void testTooManyInAFamily()
    {
        String names = "A Smith, B Smith, C Smith, D Andersen, E Andersen";
        String[] nameList = names.split(",");
        SecretSantaFinder ssf = new SecretSantaFinder(nameList);
        assertEquals(ssf.pair(),  null);
    }
    
    @Test
    public void testUnique()
    {
        String names = "A Smith, B Smith, C Smith, D Andersen, E Andersen,F Andersen, G Jackson";
        String[] nameList = names.split(",");
        SecretSantaFinder ssf = new SecretSantaFinder(nameList);
        Map<String,String> pairs = ssf.pair();

        //expected size is the same number of number of names
        assertEquals(pairs.size(),  7);
        for(String key: pairs.keySet())
        {
            //giver is not the same as receiver
            assertFalse(key.equals(pairs.get(key)));
            String[] giver = key.split(" ");
            String[] receiver = pairs.get(key).split(" ");

            //receiver and giver should not have the same last name
            assertFalse(giver[1].equals(receiver[1]));
        }
    }
}
