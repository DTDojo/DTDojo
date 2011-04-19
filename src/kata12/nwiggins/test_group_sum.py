#!/usr/bin/python
'''
Created on Apr 18, 2011

@author: nwiggins
'''
import unittest

from group_sum import GroupSum
class Test(unittest.TestCase):


    def setUp(self):
        self.groupSum = GroupSum()
    def test13(self):
        solution = self.groupSum.groupSum([1, 2, 4, 8], 13)
        self.assertEqual(solution, [1, 4, 8], "Solution for the 13 case do not match."+repr(solution))
    
    def test7(self):
        solution = self.groupSum.groupSum([1, 2, 3, 4, 5], 7)
        self.assertEqual(solution,  [2,5], "Solution for the 7 case do not match."+repr(solution))
    
    def testAllOnes(self):
        solution = self.groupSum.groupSum([1, 1, 1, 1, 1, 1, 1, 1, 1], 2)
        self.assertEqual(solution, [1, 1], "Solution for the all ones case do not match."+repr(solution))

    def testNegativeNumbers(self):
        solution = self.groupSum.groupSum([-1, -20, 20, 20, 40, 21], 20)
        self.assertEqual(solution, [20], "Solution for negative numbers do not match."+repr(solution))
    
    def testNegativeNumbersAndZero(self):
        solution = self.groupSum.groupSum([-2, -1, 0, 1, 2], 0)
        self.assertEqual(solution, [0], "Solution for the negative numbers and zero case do not match."+repr(solution))
            
    def testNoAnswer(self):
        solution = self.groupSum.groupSum([1,2,3,8], 7)
        self.assertEqual(solution, None, "An answer was found for the no answer case."+repr(solution))
    
    def testMustHaveNegative(self):
        solution = self.groupSum.groupSum([8,4,5,6,9,-2,-1,-3], 3)
        self.assertEqual(solution, [6,-3], "Negative case did not match correctly."+repr(solution))
 
if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
