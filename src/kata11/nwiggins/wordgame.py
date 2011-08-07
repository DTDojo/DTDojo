'''
Created on Dec 20, 2010

@author: nwiggins
'''
"""
"""
#
from collections import deque
class WordGame:

    def __init__(self):
        self.fourLetterMap = self.createNodeMap('../fourletterwords.txt')
        self.fiveLetterMap = self.createNodeMap('../fiveletterwords.txt')
    
    def createNodeMap(self,fileName):
        map = {}
        startsWithMap = {}
        endsWithMap = {}
        f = open(fileName)
        fileAsString = f.read()
        wordList = fileAsString.splitlines()
        for firstNode in wordList:
            startsWithList = startsWithMap[firstNode[0]] if firstNode[0] in startsWithMap else []
            startsWithList.append(firstNode)
            startsWithMap[firstNode[0]] = startsWithList
            endsWithList = endsWithMap[firstNode[1:]] if firstNode[1:] in endsWithMap else []
            endsWithList.append(firstNode)
            endsWithMap[firstNode[1:]] = endsWithList
            map[firstNode] = {'startsWith':startsWithList, 'endsWith':endsWithList, 'adjacentEdges':None}
        return map
    
    def getPath(self, begin, end):
        wordLength = len(begin)
        if (len(begin) != 4 and len(begin) != 5) or len(begin) != len(end):
            return None
        map = self.fourLetterMap if wordLength is 4 else self.fiveLetterMap
        if begin not in map or end not in map:
            return None
        minDistance = sum(x != y for x, y in zip(begin, end))
        if not minDistance:
            return [begin]
        successorMap = {}
        successorMap[begin] = begin
        queue = deque([begin])
        while queue:
            currentNode = queue.popleft()
            currentNodeSubMap = map[currentNode]
            if not currentNodeSubMap['adjacentEdges']:
                startsWithMap = currentNodeSubMap['startsWith']
                endsWithMap = currentNodeSubMap['endsWith']
                currentNodeSubMap['adjacentEdges'] = self.getAdjacentNodes(currentNode, startsWithMap, endsWithMap)
            adjacentEdges = currentNodeSubMap["adjacentEdges"]
            for x in adjacentEdges:
                if x not in successorMap:
                    successorMap[x] = currentNode
                    if x == end:
                        return self.getTraversals(x, successorMap)
                    queue.append(x)
        return None     
        
    def getTraversals(self, end, map):
        traversal_list = []
        traversal_list.append(end)
        successor = map[end]
        traversal_list.append(successor)
        while map[successor] is not successor:
            successor = map[successor]
            traversal_list.append(successor)
        traversal_list.reverse()
        return traversal_list
    
    def getAdjacentNodes(self, currentNode, startsWithList, endsWithList):
        masterList = []
        unionList = []
        unionList.extend(startsWithList)
        unionList.extend(endsWithList)
        for adj in set(unionList):
            if sum(x != y for x, y in zip(currentNode, adj)) == 1:
                masterList.append(adj)
        return masterList
