'''
Created on Nov 1, 2010

@author: nwiggins
'''
import unittest
from ConvertNumerals import ConvertNumerals

class Test(unittest.TestCase):


    def setUp(self):
        self.romanNumeralConverter = ConvertNumerals()
        pass


    def tearDown(self):
        pass


    def testOne(self):
        assert self.romanNumeralConverter.toRomanNumerals(1) == 'I'
        pass
    def testTwo(self):
        assert self.romanNumeralConverter.toRomanNumerals(2) == 'II'
        pass
    def testFour(self):
        assert self.romanNumeralConverter.toRomanNumerals(4) == 'IV'
        pass
    def testEleven(self):
        assert self.romanNumeralConverter.toRomanNumerals(11) == 'XI'
        pass
    def testFortySeven(self):
        assert self.romanNumeralConverter.toRomanNumerals(47) == 'XLVII'
        pass
    def testTwoThousandTen(self):
        assert self.romanNumeralConverter.toRomanNumerals(2010) == 'MMX'
        pass


if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()