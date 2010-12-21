'''
Created on Oct 10, 2010

@author: nwiggins
'''
import unittest
from BlockReverse import BlockReverse

class Test(unittest.TestCase):


    def testList(self):
        toReverse = BlockReverse()
        reversed = toReverse.block_reverse([1,3,5,-1,3,2])
        if reversed == [3,2,-1,1,3,5]:
            pass
        else:
            raise AssertionError
    def testListMultBlocks(self):
        toReverse = BlockReverse()
        reversed = toReverse.block_reverse([3,2,-1,1,3, -1 ,5, 8, 7, -1, 3, 4])
        if reversed == [3, 4,-1, 5, 8, 7, -1 , 1,3,-1,3,2]:
            pass
        else:
            raise AssertionError

    def testNoNegs(self):
        toReverse = BlockReverse()
        reversed = toReverse.block_reverse([1,3,5,3,2])
        if reversed == [1,3,5,3,2]:
            pass
        else:
            raise AssertionError
    def testAllNegs(self):
        toReverse = BlockReverse()
        reversed = toReverse.block_reverse([-1,-1,-1,-1,-1])
        if reversed == [-1,-1,-1,-1,-1]:
            pass
        else:
            raise AssertionError
    def testAlmostAllNegs(self):
        toReverse = BlockReverse()
        reversed = toReverse.block_reverse([-1,2,-1,-1,-1])
        if reversed == [-1,-1,-1,2,-1]:
            pass
        else:
            raise AssertionError

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testList']
    unittest.main()