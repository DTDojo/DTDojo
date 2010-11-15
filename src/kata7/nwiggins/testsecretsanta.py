'''
Created on Nov 13, 2010

@author: nwiggins
'''
import unittest
import re
from secretsanta import SecretSantaGroup

class Test(unittest.TestCase):


    def testBaseCase(self):
        ss = SecretSantaGroup()
        for x in ss.getSecretSantaListFromList("Mickey Mouse,Minnie Mouse,Donald Duck,Daisy Duck,Gladstone Gander").splitlines():
            m = re.match('^\s+\w+\s(\w+)\s+\|\s+\w+\s(\w+)\s*$', x)
            if m != None:
                assert m.group(1) != m.group(2)
        pass
    def testEnsureRandom(self):
        ss = SecretSantaGroup()
        x = 0
        while x < 1000:
            for y in ss.getSecretSantaListFromList("Mickey Mouse,Minnie Mouse,Donald Duck,Daisy Duck,Gladstone Gander").splitlines():
                m = re.match('^\s+\w+\s(\w+)\s+\|\s+\w+\s(\w+)\s*$', y)
                if m != None:
                    assert m.group(1) != m.group(2)
            x+=1
        pass
    def testEvenRandom(self):
        ss = SecretSantaGroup()
        x=0
        while x< 1000:
            for y in ss.getSecretSantaListFromList("Mickey Mouse,Minnie Mouse,Donald Duck,Daisy Duck,Gladstone Gander,Neal Wiggins").splitlines():
                m = re.match('^\s+\w+\s(\w+)\s+\|\s+\w+\s(\w+)\s*$', y)
                if m != None:
                    assert m.group(1) != m.group(2)
            x+=1
        pass
    def testFamilyTooBig(self):
        ss = SecretSantaGroup()
        doesPass = False
        try:
            ss.getSecretSantaListFromList("Mickey Mouse,Minnie Mouse,Donald Duck,Daisy Duck,Wannabe Mouse")
        except Exception as ex:
            doesPass = ex.__str__() == "Wow there, Hoss. One family's way too big now."
        assert doesPass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()