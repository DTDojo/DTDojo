import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 3, 2010
 * Time: 11:01:48 PM
 * This class tests BowlingFrame class
 */
public class TestBowlingFrame extends TestCase {
    private BowlingFrame frame;

    protected void setUp() throws Exception {
        super.setUp();
        frame = new BowlingFrame();
    }


    @Override
    protected void tearDown() throws Exception {
        frame = null;
        super.tearDown();
    }

    public void testIncorrectNumberPinsForOneFrameRoll() {
        try {
            frame.addPins(11);
            fail("Number of pins in a roll cannot exceed 10, expect IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    public void testIncorrectNumberOfPinsForFrame() {
        frame.addPins(7);
        try {
            frame.addPins(4);
            fail("Number of pins in a frame cannot exceed 10, expect IllegalArgumentException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    public void testNormalFrame() {
        frame.addPins(3);
        frame.addPins(5);
        assertTrue(frame.isFrameDone());
        assertFalse(frame.needsBonus());
        assertEquals(8, frame.getFrameScore());
    }

    public void testSpareNoBonus() {
        frame.addPins(6);
        frame.addPins(4);
        assertFalse(frame.isFrameDone());
        assertTrue(frame.needsBonus());
        assertEquals(10, frame.getFrameScore());    
    }
    public void testSpareWithBonus() {
        frame.addPins(6);
        frame.addPins(4);
        assertFalse(frame.isFrameDone());
        assertTrue(frame.needsBonus());
        frame.addPins(7); //bonus
        assertTrue(frame.isFrameDone());
        assertFalse(frame.needsBonus());
        assertEquals(17, frame.getFrameScore());    
    }
    public void testStrikeNoBonus() {
        frame.addPins(10);
        assertFalse(frame.isFrameDone());
        assertTrue(frame.needsBonus());
        assertEquals(10, frame.getFrameScore());
    }
    public void testStrikeWith1BonusRoll() {
        frame.addPins(10);
        frame.addPins(4); //bonus
        assertFalse(frame.isFrameDone());
        assertTrue(frame.needsBonus());
        assertEquals(14, frame.getFrameScore());
    }
    public void testStrikeWith2BonusRolls() {
        frame.addPins(10);
        frame.addPins(4); //bonus
        frame.addPins(9); //bonus
        assertTrue(frame.isFrameDone());
        assertFalse(frame.needsBonus());
        assertEquals(23, frame.getFrameScore()); 
    }

    public void testIncorrectNumberOfRolls() {
        frame.addPins(6);
        frame.addPins(4);
        frame.addPins(4);
        try {
            frame.addPins(5);
            fail("Spare frame cannot accept any more rolls, expect IllegalStateException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }
}
