'''
Created on Dec 20, 2010

@author: nwiggins
'''

class WordGame(object):


    def __init__(self):
        self.fourLetterMap = self.createNodeMap("../fourletterwords.txt")
        self.fiveLetterMap = self.createNodeMap("../fiveletterwords.txt")
    
    def createNodeMap(self,fileName):
        map = {}
        startsWithMap = {}
        endsWithMap = {}
        f = open(fileName)
        fileAsString = f.read()
        wordList = fileAsString.splitlines()
        for firstNode in wordList:
            startsWithList = startsWithMap[firstNode[0]] if startsWithMap.has_key(firstNode[0]) else []
            startsWithList.append(firstNode)
            startsWithMap[firstNode[0]] = startsWithList
            endsWithList = endsWithMap[firstNode[1:]] if endsWithMap.has_key(firstNode[1:]) else []
            endsWithList.append(firstNode)
            endsWithMap[firstNode[1:]] = endsWithList
            map[firstNode] = {"startsWith":startsWithList,"endsWith":endsWithList,"adjacentEdges":None}
        return map
    
    def getPath(self,begin, end):
        wordLength = len(begin)
        if (len(begin) != 4 and len(begin) !=5) or len(begin) != len(end):
            return None
        if wordLength is 4:
            map = self.fourLetterMap
        else:
            map = self.fiveLetterMap
        if map.has_key(begin)==False or map.has_key(end)==False:
            return None
        minDistance = sum(x != y for x,y in zip(begin,end))
        if minDistance == 0:
            return [(begin)]
        successorMap = {}
        successorMap[begin] = begin
        queue = []
        queue.append(begin)
        while len(queue) > 0:
            currentNode = queue[0]
            del queue[0]
            currentNodeSubMap = map[currentNode]
            if currentNodeSubMap["adjacentEdges"] == None:
                currentNodeSubMap["adjacentEdges"] = self.getAdjacentNodes(currentNode,currentNodeSubMap["startsWith"],currentNodeSubMap["endsWith"])
            adjacentEdges = list(currentNodeSubMap["adjacentEdges"]) 
            for x in adjacentEdges:
                if successorMap.has_key(x)==False:
                    successorMap[x] = currentNode
                    if x == end:
                        return self.getTraversals(x,successorMap)
                    queue.append(x)
        return None     
        
    def getTraversals(self,end,map):
        myList = []
        myList.append(end)
        successor = map[end]
        myList.append(successor)
        while map[successor] != successor:
            successor = map[successor]
            myList.append(successor)
        myList.reverse()
        return myList
    
    def getAdjacentNodes(self,currentNode,startsWithList,endsWithList):
        masterList = []
        unionList = []
        unionList.extend(list(set(startsWithList).union(set(endsWithList))))
        for adj in unionList:
            if sum(x != y for x,y in zip(currentNode,adj)) == 1:
                masterList.append(adj)
        return masterList
        
        
        
