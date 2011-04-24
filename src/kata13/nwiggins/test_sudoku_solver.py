'''
Created on Apr 23, 2011

@author: nwiggins
'''
import unittest
from sudoku_solver import SudokuSolver

class Test(unittest.TestCase):


    def testEasy(self):
        solver = SudokuSolver()
        solver.solve('../easypuzzle.in')
        result = solver.printBoard()
        assert 'x' not in result, result
        pass
    def testMedium(self):
        solver = SudokuSolver()
        solver.solve('../mediumpuzzle.in')
        result = solver.printBoard()
        assert 'x' not in result, result
        pass
    def testHard(self):
        solver = SudokuSolver()
        solver.solve('../hardpuzzle.in')
        result = solver.printBoard()
        assert 'x' not in result, result
        pass
    def testEvil(self):
        solver = SudokuSolver()
        solver.solve('../evilpuzzle.in')
        result = solver.printBoard()
        assert 'x' not in result, result
        pass


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()