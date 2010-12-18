'''
Created on Dec 12, 2010

@author: nwiggins
'''
import unittest
from countlines import CountLines

class Test(unittest.TestCase):

    def testNestedSingleLine(self):
        testaroo = CountLines()
        newString = testaroo.strippedMultiLineComments('Test a st/*hi*/ri/*blah*/ng lik/*blahblah*/e this')
        assert newString == 'Test a string like this'
        pass
    def testNestedMultiLine(self):
        testaroo = CountLines()
        newString = testaroo.strippedMultiLineComments('This is a /*t\ne/*s*/\n/*t*/\n*/ of the JavaScript RegExp object')
        assert newString == 'This is a \n of the JavaScript RegExp object'
        pass
    def testMultipleNestedMultiLine(self):
        testaroo = CountLines()
        newString = testaroo.strippedMultiLineComments('This is a /*t\ne/*s*/\n/*t*/\n*/ of the Java/*Script*/ RegExp object')
        assert newString == 'This is a \n of the Java RegExp object'
        pass
    def testEndLineComments(self):
        testaroo = CountLines()
        newString = testaroo.stripOutLineComments('This is a //test of the JavaScript RegExp object')
        assert newString == 'This is a '
        pass
    def testStripOutEmptyLines(self):
        testaroo = CountLines()
        newString = testaroo.stripOutBlankLines('test\r  \t  \na')
        assert newString == 'test\na'
        pass
    def testJavaFileForLineCount(self):
        f = open('../asantoro/fourlinefile.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 4
        pass
    def testJavaEmptyBracesFileCount(self):
        f = open('../asantoro/emptybraces.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 4
        pass
    def testJavaHasBlankSpaceFileCount(self):
        f = open('../asantoro/hasblankspace.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 3
        pass
    def testJavaMultiLineCommentsFileCount(self):
        f = open('../asantoro/multilinecomments.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 4
        pass
    def testJavaThreeLineFileCount(self):
        f = open('../asantoro/threelinefile.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 3
        pass
    def testJavaThreeLinesAndCommentFileCount(self):
        f = open('../asantoro/threelinesandcomment.java')
        javaFile = f.read()
        testaroo = CountLines()
        lineCount = testaroo.numberOfLines(javaFile)
        assert lineCount == 3
        pass

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()
