'''
Created on Dec 20, 2010

@author: nwiggins
'''
import unittest

from wordgame import WordGame
class Test(unittest.TestCase):


    def setUp(self):
        self.game = WordGame()
        pass


    def tearDown(self):
        pass


    def testCreateFourWordMap(self):
        map = self.game.createNodeMap("../fourletterwords.txt")
        assert map != None
        pass
    def testCreateFiveWordMap(self):
        map = self.game.createNodeMap("../fiveletterwords.txt")
        assert map != None
        pass
    def testSameFourWords(self):
        path = self.game.getPath('test', 'test')
        assert len(path) == 1
        pass
    def testSameFiveWords(self):
        path = self.game.getPath('place', 'place')
        assert len(path) == 1
        pass
    def testUnion(self):
        test = self.game.getAdjacentNodes("test", ['text', 'pest'], ['turn', 'text'])
        assert len(test) == 2
        pass
    def testGame(self):
        test = self.game.getPath('boat', 'toad')
        assert len(test) == 4
        assert test == ['boat', 'goat', 'goad', 'toad']
        pass
    def testLongGame(self):
        test = self.game.getPath('slams', 'sonly')
        assert len(test) == 10
        pass
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()