'''
Created on Nov 21, 2010

@author: nwiggins
'''
import unittest

from familyfinder import FamilyTree
class Test(unittest.TestCase):

    def setUp(self):
        myFamily = FamilyTree()
        myFamily.add_parent_child("Anna Smith", "Peter Paul Johansen")
        myFamily.add_parent_child("John Smith", "Charles Smith") # john is the father of charles
        myFamily.add_parent_child("Charles Smith","Nelson Smith" )
        myFamily.add_parent_child("John Smith", "Anna Smith")
        myFamily.add_parent_child("Jim Smith", "John Smith")
        myFamily.add_parent_child("Jim Smith", "Jack Smith")
        self.familyToUse = myFamily

    def testSibling(self):
        assert self.familyToUse.relationship("Anna Smith", "Charles Smith") == "SIBLING"
        pass
    def testParent(self):
        assert self.familyToUse.relationship("John Smith", "Charles Smith") == "PARENT"
        pass
    def testChild(self):    
        assert self.familyToUse.relationship("Charles Smith", "John Smith") ==  "CHILD"
        pass
    def testStranger(self):    
        assert self.familyToUse.relationship("Zachary Smith", "Charles Smith") ==  "STRANGER"
        pass
    def testGrandParent(self):
        assert self.familyToUse.relationship("John Smith", "Peter Paul Johansen") == "GRANDPARENT"
        pass
    def testGrandChild(self):    
        assert self.familyToUse.relationship("Peter Paul Johansen", "John Smith") ==  "GRANDCHILD"
        pass
    def testCousins(self):    
        assert self.familyToUse.relationship("Peter Paul Johansen", "Nelson Smith") ==  "COUSIN"
        pass
    def testAunt(self):    
        assert self.familyToUse.relationship("Anna Smith", "Nelson Smith") ==  "UNCLE/AUNT"
        pass
    def testNephew(self):    
        assert self.familyToUse.relationship("Nelson Smith","Anna Smith") == "NEPHEW/NIECE" 
        pass
    def testRelated(self):    
        assert self.familyToUse.relationship("Nelson Smith","Jack Smith") == "RELATED" 
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()