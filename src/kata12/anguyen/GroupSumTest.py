from GroupSum import GroupSum
import unittest

class Test(unittest.TestCase):
    
    def test13(self):
        solutions = GroupSum.groupSum([1, 2, 4, 8], 13)
        self.assertEqual(solutions, [(1, 4, 8)], "Solutions for the 13 case do not match.")
    
    def test7(self):
        solutions = GroupSum.groupSum([1, 2, 3, 4, 5], 7)
        self.assertEqual(solutions, [(1, 2, 4), (2, 5), (3, 4)], "Solutions for the 7 case do not match.")
    
    def testAllOnes(self):
        solutions = GroupSum.groupSum([1, 1, 1, 1, 1, 1, 1, 1, 1], 2)
        self.assertEqual(solutions, [(1, 1)], "Solutions for the all ones case do not match.")

    def testNegativeNumbers(self):
        solutions = GroupSum.groupSum([-1, -20, 20, 20, 40, 21], 20)
        self.assertEqual(solutions, [(-20, -1, 20, 21), (-20, 20, 20), (-20, 40), (-1, 21), (20,)], "Solutions for negative numbers do not match.")
    
    def testNegativeNumbersAndZero(self):
        solutions = GroupSum.groupSum([-2, -1, 0, 1, 2], 0)
        self.assertEqual(solutions, [(-2, -1, 0, 1, 2), (-2, -1, 1, 2), (-2, 0, 2), (-2, 2), (-1, 0, 1), (-1, 1), (0,)], "Solutions for the negative numbers and zero case do not match.")
            
    def testNoAnswer(self):
        solutions = GroupSum.groupSum([1,2,3,8], 7)
        self.assertEqual(solutions, [], "An answer was found for the no answer case.")

    



if __name__ == "__main__":
    unittest.main()