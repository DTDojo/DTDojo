'''
Created on Oct 31, 2010

@author: Andrew
'''
import unittest
from RomanNumeralConverter import RomanNumeralConverter


class Test(unittest.TestCase):
   
    def test_1(self):
        result = RomanNumeralConverter.convertNumber(1)
        self.assertEqual("I", result, "Failed case 1; returned " + result)
    
    def test_4(self):
        result = RomanNumeralConverter.convertNumber(4)
        self.assertEqual("IV", result, "Failed case 4; returned " + result)
    
    def test_9(self):
        result = RomanNumeralConverter.convertNumber(9)
        self.assertEqual("IX", result, "Failed case 9; returned " + result)
    
    def test_40(self):
        result = RomanNumeralConverter.convertNumber(40)
        self.assertEqual("XL", result, "Failed case 40; returned " + result)
    
    def test_45(self):
        result = RomanNumeralConverter.convertNumber(45)
        self.assertEqual("XLV", result, "Failed case 45; returned " + result)
    
    def test_90(self):
        result = RomanNumeralConverter.convertNumber(90)
        self.assertEqual("XC", result, "Failed case 90; returned " + result)
    
    def test_400(self):
        result = RomanNumeralConverter.convertNumber(400)
        self.assertEqual("CD", result, "Failed case 400; returned " + result)
    
    def test_900(self):
        result = RomanNumeralConverter.convertNumber(900)
        self.assertEqual("CM", result, "Failed case 900; returned " + result)
    
    def test_949(self):
        result = RomanNumeralConverter.convertNumber(949)
        self.assertEqual("CMXLIX", result, "Failed case 949; returned " + result)
    
    def test_2090(self):
        result = RomanNumeralConverter.convertNumber(2090)
        self.assertEqual("MMXC", result, "Failed case 2090; returned " + result)
        
    def test_3888(self):
        result = RomanNumeralConverter.convertNumber(3888)
        self.assertEqual("MMMDCCCLXXXVIII", result, "Failed case 3888; returned " + result)

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()