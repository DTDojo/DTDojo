Bowling Kata
--------------------
After years of automation, people no longer are able to calculate their own bowling scores, so it is your responsibility t
o write a bowling score program for them. Using your favorite programming language, write a class or method for calculatin
g a player's totals.

For example, you could write the following:

    george = Bowler()
    george.rolls(3)
    george.rolls(6)
    george.rolls(10)
    ...
    println "George's total is " + george.getTotal()

or, if you prefer to take a one function does all approach:

    rolls = [ 3, 6, 10, .... ]
    println "George's total is " + bowlingTotal( rolls )

If you don't know how bowling is scored, you can find the information [here](http://www.bowling2u.com/trivia/game/scoring.asp)

And some test cases:

1. A perfect game of 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10 should total 300 (note the two extra balls at the end, because the last frame was a strike)
2. A game of spares 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1 should total 110 (with one extra ball at the end because the last frame was a spare)
3. A game of spares 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9 should total 190 (also with the extra ball)
4. A game with no spares 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3 should total 40