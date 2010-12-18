'''
Created on Dec 12, 2010

@author: nwiggins
'''
import re
class CountLines(object):

    def strippedMultiLineComments(self,javaCode):
        javaCode = re.sub('\/\*.*?\*\/', '', javaCode)
        return re.sub('/\*([^*]|\*[^/])*\*/', '\n', javaCode,re.MULTILINE)
    def stripOutLineComments(self,javaCode):
        return re.sub('//.*','',javaCode)
    def stripOutBlankLines(self,javaCode):
        javaCode = re.sub('[\n\r](\s|[\{\}])*[\n\r]*','\n',javaCode,re.MULTILINE)
        return re.sub('^\s*','',javaCode,re.MULTILINE)
    def numberOfLines(self, javaCode):
        javaCode = self.strippedMultiLineComments(javaCode)
        javaCode = self.stripOutLineComments(javaCode)
        javaCode = self.stripOutBlankLines(javaCode)
        return len(javaCode.splitlines())
