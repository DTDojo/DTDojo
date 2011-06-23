Cyclic Words Kata
---------------------------
Words are cyclically equivalent if they can be made the same by repeatedly moving the last character to the front. For example, 'abcd', 'bcda' and 'dabc' are all cyclically equivaltent, while 'abba' and 'abab' are not.

So, given an array of words, return an array of arrays where all the words in a subarray are cyclically equivalent. Or, if you prefer, return a map of such words.

Some test cases:

  * [ 'abc' ] should return [ [ 'abc' ]
  * [ 'abc', 'bca', 'cde' ] should return [ [ 'abc', 'bca' ], [ 'cde'] ] 

The order is not really important, only that the subarrays exist and are correct. 
