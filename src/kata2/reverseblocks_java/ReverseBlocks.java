
/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 9, 2010
 * Time: 6:21:24 PM
 * This class reverses block order in array
 *
 */
public class ReverseBlocks {

    /**
     * This method reverses blocks of array
     * @param arrToReverse  int[] array of integers to reverse blocks
     * @return   int[] array of integers
     */
    public int[] reverseArrayBlocks(int[] arrToReverse) {
        return reverseArrayBlocks(arrToReverse, -1);
    }

    /**
     * This method reverses blocks of array
     * @param arrToReverse  int[] array of integers to reverse blocks
     * @param blockseparator  int separating the block of integers in an array
     * @return   int[] array of integers
     */
    public int[] reverseArrayBlocks(int[] arrToReverse, int blockseparator) {
        //no point in reversing null, empty or array of one element
        if (arrToReverse != null && arrToReverse.length > 1) {
            // reverse the whole array in place
            reverseInPlace(arrToReverse, 0, arrToReverse.length - 1);
            // now we have all the blocks in the correct order, we need to reverse the content of the blocks
            boolean hasBlock = false;
            int blockStart = 0;
            for (int i = 0; i < arrToReverse.length; i++) {
                if (arrToReverse[i] == blockseparator) {
                    if (hasBlock) {
                        if (i > blockStart + 1) {
                            reverseInPlace(arrToReverse, blockStart, (i - 1));
                        }
                        hasBlock = false;
                    }
                } else {
                    // if start of the block is -1, start counting
                    if (!hasBlock) {
                        blockStart = i;
                        hasBlock = true;
                    }
                }
            }
            if (hasBlock && blockStart < (arrToReverse.length - 2)) {
                //reverse the last block
                reverseInPlace(arrToReverse, blockStart, arrToReverse.length - 1);
            }            
        }
        return arrToReverse;
    }


    private void reverseInPlace(int[] arr, int start, int end) {
        if (arr != null && arr.length > 0) {
            if (start < 0 || start >= arr.length || end < 0 || end >= arr.length || end <= start) {
                throw new IllegalArgumentException("Incorrect starting and ending point of array to reverse");
            }
            int temp;
            for (int i = start, j = end; i < j; i++, j--) {
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
}
