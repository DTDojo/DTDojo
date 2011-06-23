Word Game Kata
-------------------------

Your Xmas guests got bored with playing Clue and want a new game. You suggest that they play a word puzzle game where you give them two words and they have to connect them using a sequence of words that must satisfy the following two criteria:

 * They are valid English words
 * They differ from the previous word by only one letter. 

For example, to go from "boat" to "ship":

  boat -> boot -> soot -> shot -> shop -> ship

Of course, you are too busy to play the game yourself so you write a program to play it for you. The program should, given a dictionary file and two words from the dictionary, print out the sequence as above, or "unsolvable" if it cannot find a solution.

To help you along, here are two dictionary files of english words, with one word per line:

 * [fourletterwords.txt](http://github.com/DTDojo/DTDojo/blob/master/src/kata11/fourletterwords.txt)
 * [fiveletterwords.txt](http://github.com/DTDojo/DTDojo/blob/master/src/kata11/fiveletterwords.txt)