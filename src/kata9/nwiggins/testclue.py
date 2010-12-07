'''
Created on Dec 6, 2010

@author: nwiggins
'''
import unittest
from cluegameplayer import Game, Player

class Test(unittest.TestCase):

    def testFullGame(self):
        game = Game([ 'van Helsing', 'Wyatt Earp' ] , [ 'stake', 'gun' ], [ 'cemetery', 'OK Corral' ])
        player = Player([ 'van Helsing', 'Wyatt Earp' ] , [ 'stake', 'gun' ], [ 'cemetery', 'OK Corral' ])
        solution = player.solve(game)
        assert solution != None
        print solution
        pass


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()