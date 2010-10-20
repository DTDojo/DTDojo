'''
Created on Oct 17, 2010

@author: nwiggins
'''
import unittest
from CyclicWords import CyclicWords

class Test(unittest.TestCase):
    
    
    def setUp(self):
        self.me = CyclicWords()
        pass

    def tearDown(self):
        self.me = None
        pass
    
    def testNormalCase(self):
        valuesBack = self.me.getCyclicGroupings([ 'abc', 'bca', 'cde' ])
        assert valuesBack == [['abc', 'bca'], ['cde']]
        pass
    
    def testNormal2Case(self):
        valuesBack = self.me.getCyclicGroupings([ 'abc', 'cab', 'cde' ])
        assert valuesBack == [['abc', 'cab'], ['cde']]
        pass
    
    def testExampleCase(self):
        valuesBack = self.me.getCyclicGroupings(['abcd', 'bcda' , 'dabc' ,'abba' ,'abab' ])
        assert valuesBack == [['abcd', 'bcda' , 'dabc'] ,['abba'] ,['abab' ]]
        pass
    
    def testDuplicateInSecondInstanceCase(self):
        valuesBack = self.me.getCyclicGroupings([ 'abc', 'cab','bca','cab', 'cde' ])
        assert valuesBack == [['abc', 'cab', 'bca'], ['cde']]
        pass
    
    def testDupeCase(self):
        valuesBack = self.me.getCyclicGroupings([ 'abc', 'abc', 'cde' ])
        assert valuesBack == [['abc'], ['cde']]
        pass
    
    def testNormalNoneCase(self):
        valuesBack = self.me.getCyclicGroupings([ 'acb', 'bca', 'cde' ])
        assert valuesBack == [['acb'], ['bca'], ['cde']]
        pass
    
    def testNoneCase(self):
        valuesBack = self.me.getCyclicGroupings([ 'adc', 'bca', 'cde' ])
        assert valuesBack == [['adc'], ['bca'], ['cde']]
        pass

if __name__ == "__main__":
    unittest.main()
