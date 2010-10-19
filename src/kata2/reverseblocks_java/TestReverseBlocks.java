import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 9, 2010
 * Time: 6:56:09 PM
 * This class tests ReverseBlocks class
 */
public class TestReverseBlocks extends TestCase {
    private ReverseBlocks blockReversal;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        blockReversal = new ReverseBlocks();
    }

    @Override
    protected void tearDown() throws Exception {
        blockReversal = null;
        super.tearDown();
    }

    public void testReverseNullArray() {
        assertNull(blockReversal.reverseArrayBlocks(null));
    }

    public void testReverseEmptyArray() {
        assertEquals(0, (blockReversal.reverseArrayBlocks(new int[]{})).length);
    }

    public void testReverseOneElementArray() {
        int[] expected = {1};
        int[] actual = blockReversal.reverseArrayBlocks(new int[]{1});
        assertArrays(expected, actual);
    }

    public void testReverseOneElementArray_Minus1() {
        int[] expected = {-1};
        assertArrays(expected, blockReversal.reverseArrayBlocks(new int[]{-1}));
    }

    public void testReverseArrayofMinus1s() {
        int[] expected = {-1, -1, -1, -1};
        assertArrays(expected, blockReversal.reverseArrayBlocks(new int[]{-1, -1, -1, -1}));
    }

    public void testReverseArray() {
        int[] arr = {-1, -1, 1, 2, 3, -1, 4, 5, -1, -1, -1, 6, 7, 8, -1, 9, -1};
        int[] expected = {-1, 9, -1, 6, 7, 8, -1, -1, -1, 4, 5, -1, 1, 2, 3, -1, -1};
        assertArrays(expected, blockReversal.reverseArrayBlocks(arr));
    }

    public void testReverseArrayNoSeparator() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] expected = {1, 2, 3, 4, 5, 6, 7};
        assertArrays(expected, blockReversal.reverseArrayBlocks(arr));
    }

    public void testReverseArray_BlockSeparator3() {
        int[] arr = {3, -1, 1, 2, 3, -1, 4, 5, -1, -1, 3, 6, 7, 8, -1};
        int[] expected = {6, 7, 8, -1, 3, -1, 4, 5, -1, -1, 3, -1, 1, 2, 3};
        assertArrays(expected, blockReversal.reverseArrayBlocks(arr, 3));
    }

    private void assertArrays(int[] expected, int[] actual) {
        assertNotNull(actual);
        assertEquals(expected.length, actual.length);
        for (int i = 0; i < actual.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }


}
