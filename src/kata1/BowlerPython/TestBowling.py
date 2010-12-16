'''
Created on Oct 4, 2010

@author: nwiggins
'''
import unittest
from kata1.Bowler import Bowler

class Test(unittest.TestCase):

    def testIncompleteStrikeGame(self):
        incompleteStrikeGame = Bowler()
        for i in [10,2]:
            incompleteStrikeGame.rolls(i)
        incompleteStrikeGameScore = incompleteStrikeGame.currentScore()
        if incompleteStrikeGameScore == 0:
            pass
        else:
            raise AssertionError("the perfect game should be 0 but was "+str(incompleteStrikeGameScore))
        
    def testCompleteStrikeGame(self):
        completeStrikeGame = Bowler()
        for i in [10,2,2]:
            completeStrikeGame.rolls(i)
        completeStrikeGameScore = completeStrikeGame.currentScore()
        if completeStrikeGameScore == 18:
            pass
        else:
            raise AssertionError("the perfect game should be 18 but was "+str(completeStrikeGameScore))
        
    def testPefectGame(self):
        perfectGame = Bowler()
        for i in [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]:
            perfectGame.rolls(i)
        perfectGameScore = perfectGame.currentScore()
        if perfectGameScore == 300:
            pass
        else:
            raise AssertionError("the perfect game should be 300 but was "+str(perfectGameScore))
    def testTooManyPefectGame(self):
        perfectGame = Bowler()
        for i in [10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10]:
            perfectGame.rolls(i)
        try:
            perfectGame.rolls(10)
        except AssertionError:
            pass
        
    def testWeakGameOfSpares(self):
        weakGameOfSpares = Bowler()
        for i in [1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1]:
            weakGameOfSpares.rolls(i)
        weakGameOfSparesScore = weakGameOfSpares.currentScore()
        if weakGameOfSparesScore == 110:
            pass
        else:
            raise AssertionError("the weakGameOfSpares game should be 110 but was "+str(weakGameOfSparesScore))
    def testTooManySpares(self):
        weakGameOfSpares = Bowler() 
        for i in [1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1]:
            weakGameOfSpares.rolls(i)
        try:
            weakGameOfSpares.rolls(1)
        except AssertionError:
            pass
        
    def testStrongGameOfSpares(self):
        strongGameOfSpares = Bowler()
        for i in [9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1, 9, 1,9]:
            strongGameOfSpares.rolls(i)
        strongGameOfSparesScore = strongGameOfSpares.currentScore()
        if strongGameOfSparesScore == 190:
            pass
        else:
            raise AssertionError("the strongGameOfSpares game should be 190 but was " + str(strongGameOfSparesScore))
        
    def testWeakGame(self):
        weakGame = Bowler()
        for i in [1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3]:
            weakGame.rolls(i)
        weakGameScore = weakGame.currentScore()
        if weakGameScore == 40:
            pass
        else:
            raise AssertionError("the weakGame game should be 40 but was "+str(weakGameScore))
    def testTooManyWeakGame(self):
        weakGame = Bowler()
        for i in [1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3, 1, 3]:
            weakGame.rolls(i)
        try:
            weakGame.rolls(1)
        except AssertionError:
            pass
    
    def testZeroTenSpareGame(self):
        weakGame = Bowler()
        weakGame.rolls(0)
        weakGame.rolls(10)
        weakGame.rolls(3)
        weakGameScore = weakGame.currentScore()
        if weakGameScore == 13:
            pass
    def testZeroElevenSpareGame(self):
        weakGame = Bowler()
        weakGame.rolls(0)
        try:
            weakGame.rolls(11)
        except AssertionError:
            pass
    def testOneTenSpareGame(self):
        weakGame = Bowler()
        weakGame.rolls(1)
        try:
            weakGame.rolls(10)
        except AssertionError:
            pass
        
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()