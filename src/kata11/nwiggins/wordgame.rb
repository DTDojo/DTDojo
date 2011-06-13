class WordGame
    def initialize()
        @fourLetterMap = createNodeMap('../fourletterwords.txt')
        @fiveLetterMap = createNodeMap('../fiveletterwords.txt')
    end

    def createNodeMap(fileName)
        map = {}
        startsWithMap = {}
        endsWithMap = {}
        wordList = IO.readlines(fileName).map { |line| line.chomp }
        wordList.each do |firstNode|
            startsWithList = startsWithMap[firstNode[0]] !=nil ? startsWithMap[firstNode[0]] :  []
            startsWithList.push(firstNode)
            startsWithMap[firstNode[0]] = startsWithList
            endsWithList = endsWithMap[firstNode[1,]] != nil ? endsWithMap[firstNode[1,]] : []
            endsWithList.push(firstNode)
            endsWithMap[firstNode[1,]] = endsWithList
            map[firstNode] = {"startsWith"=>startsWithList,"endsWith"=>endsWithList,"adjacentEdges"=>nil}
        end
        return map
    end

    def getAdjacentNodes(currentNode,startsWithList,endsWithList)
        masterList = []
        unionList = startsWithList | endsWithList
        unionList.each do |adj|
            if strDiff(currentNode,adj) == 1
                masterList.push(adj)
            end
        end
        return masterList
    end

    def getPath(beginning, ending)
        wordLength = beginning.length
        if (beginning.length != 4 and beginning.length !=5) or beginning.length != ending.length
            return nil
        end
        if wordLength == 4
            map = @fourLetterMap
        else
            map = @fiveLetterMap
        end
        if map[beginning]==nil or map[ending]==nil:
            return nil
        end
        if beginning == ending
            return [(beginning)]
        end
        successorMap = {}
        successorMap[beginning] = beginning
        queue = []
        queue.push(beginning)
        while queue.length > 0
            currentNode = queue[0]
            queue.delete_at(0)
            currentNodeSubMap = map[currentNode]
            if currentNodeSubMap["adjacentEdges"] == nil
                startsWithMap = currentNodeSubMap["startsWith"]
                endsWithMap = currentNodeSubMap["endsWith"]
                currentNodeSubMap["adjacentEdges"] = getAdjacentNodes(currentNode,startsWithMap,endsWithMap)
            end
            adjacentEdges = Array.new(currentNodeSubMap["adjacentEdges"])
            adjacentEdges.each do |x|
                if successorMap[x]==nil
                    successorMap[x] = currentNode
                    if x == ending
                        return getTraversals(x,successorMap)
                    end
                    queue.push(x)
                end
            end
        end
        return nil
    end

    def getTraversals(ender,map)
        myList = []
        myList.push(ender)
        successor = map[ender]
        myList.push(successor)
        while map[successor] != successor
            successor = map[successor]
            myList.push(successor)
        end
        myList.reverse!
        return myList
    end

    def strDiff(a,b)
        if a.length != b.length
            return -1
        end
        d = 0
        a.length.times do |i|
            d += 1 if a[i] != b[i]
        end
        return d
    end

end