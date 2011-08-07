'''
Created on Oct 10, 2010

@author: nwiggins
'''

class BlockReverse:
    
    def block_reverse(self, originalList):
        blockBegin = 0
        stack = []
        for x in range(len(originalList) - 1):
            if originalList[x] == -1:
                stack.append(originalList[blockBegin:x])
                stack.append([-1])
                blockBegin = x + 1
        stack.append(originalList[blockBegin:])
        reversed = []
        while stack:
            x = stack.pop()
            for i in x:
                reversed.append(i)
        return reversed
