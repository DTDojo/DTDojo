Sudoku Kata
------------------

A sudoku puzzle consists of a 9 × 9&#8211;square grid subdivided into nine 3 × 3 boxes. Some of the squares contain numbers. The object is to fill in the remaining squares so that every row, every column, and every 3 × 3 box contains each of the numbers from 1 to 9 exactly once. 

Write a program that takes as an input a file containing a sudoku puzzle, and prints the result for that puzzle.
The file will contain 9 lines, each consisting of 9 characters, corresponding to the initial value for each square, with 'x' for squares with an unknown value.

For example, the input file containing:

    586xxxx12
    xxxx5286x
    24x81xxx3
    xxx5x3x9x
    xxxx8124x
    4x56xx738
    x5x23xx81
    7xxxx8xxx
    36xxx5xxx

should print out the following result:

    586374912
    137952864
    249816573
    872543196
    693781245
    415629738
    954237681
    721468359
    368195427

Assume that no __back-tracking__ (guesswork) is necessary, the puzzles should contain enough values to allow a unique solution to be found without a problem.

The four puzzles, ranging in difficulty from easy to evil that I got from http://websudoku.com:
   
1. [http://www.github.com/DTDojo/DTDojo/tree/master/src/kata13/easypuzzle.in]()
2. [http://www.github.com/DTDojo/DTDojo/tree/master/src/kata13/mediumpuzzle.in]()
3. [http://www.github.com/DTDojo/DTDojo/tree/master/src/kata13/hardpuzzle.in]()
4. [http://www.github.com/DTDojo/DTDojo/tree/master/src/kata13/evilpuzzle.in]()
