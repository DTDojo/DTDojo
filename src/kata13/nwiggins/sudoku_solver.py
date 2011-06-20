'''
Created on Apr 9, 2011

@author: nwiggins
'''

class SudokuSolver:
    def solve(self, fileName): 
        file = open(fileName)
        string = file.read()
        self.board = Board()
        self.board.setKnownValues(string)
        self.board.findUniqueValues()
        if self.board.isSolved(): #most can be solved at this point
            return
        prevBoard = ''
        currentBoard = self.printBoard()
        while prevBoard != currentBoard:
            prevBoard = currentBoard
            self.board.useGroupsToRefine()
            self.board.findUniqueValues()
            self.board.ruleOfK()
            currentBoard = self.printBoard()
            
    def printBoard(self):
        return self.board.getBoardString()
    
    def printTilePossibleValues(self):
        return self.board.getColumnPossibleValues()
    
class Tile:
    def __init__(self, row, column):
        self.possibleValues = [1, 2, 3, 4, 5, 6, 7, 8, 9]
        self.row = row
        self.column = column
        
    def removeValueFromPossibleValues(self, value):
        if self.possibleValues.count(value) > 0:
            self.possibleValues.remove(value) 
            return True
        return False
    
    def isTileSolved(self):
        return len(self.possibleValues) == 1
    
    def setTileToValue(self, value):
        self.possibleValues = list()
        self.possibleValues.append(value)
        
    def getPossibleValues(self):
        return self.possibleValues
    
    def getTileRow(self):
        return self.row
    
    def getTileColumn(self):
        return self.column

class Board:
    def __init__(self):
        self.board = list()
        self.tileToGroups = dict()
        rowGroup = list()
        columnGroup = list()
        blockGroup = list()
        for row in range(0, 9):
            self.board.append(list())
            rowGroup.append(Group())
            if len(blockGroup) <= (row / 3):
                blockGroup.append(list())
            for column in range(0, 9):
                if len(columnGroup) <= column:
                    columnGroup.append(Group())
                if len(blockGroup[row / 3]) <= (column / 3):
                    blockGroup[row / 3].append(Group())
                currentTile = Tile(row + 1, column + 1)
                self.board[row].append(currentTile)
                groupList = list()
                groupList.append(rowGroup[row])
                groupList.append(columnGroup[column])
                groupList.append(blockGroup[row / 3][column / 3])
                for group in groupList:
                    group.addTileToGroup(currentTile)
                self.tileToGroups[currentTile] = groupList
        self.rowGroup = rowGroup
        self.columnGroup = columnGroup
        self.blockGroup = blockGroup
        
    def setKnownValues(self, knownValues):
        lines = knownValues.splitlines()
        for row in range(0, 9):
            line = lines[row]
            for column in range(0, 9):
                valueString = line[column]
                if valueString != 'x':
                    value = int(valueString)
                    tile = self.board[row][column]
                    tile.setTileToValue(value)
                    affectedTiles = list(set(self.updateGroupsByTile(tile)))
                    while len(affectedTiles) > 0:
                        affectedTiles.extend(self.updateGroupsByTile(affectedTiles[0]))
                        del affectedTiles[0]
                        affectedTiles = list(set(affectedTiles))

    def updateGroupTilesOnFunction(self, functionName):
        tilesUpdated = list()
        for row in range(0, 9):
            for column in range(0, 9):
                tile = self.board[row][column]
                groupList = self.tileToGroups[tile]
                for group in groupList:
                    tilesUpdated.extend(getattr(group, functionName)())
        self.updateAffectedTiles(tilesUpdated)
            
    def findUniqueValues(self):
        self.updateGroupTilesOnFunction('findOnlyOneInGroup')
    
    def ruleOfK(self):
        self.updateGroupTilesOnFunction('applyRuleOfK')
            
    def updateGroupsByTile(self, tile):
        groupList = self.tileToGroups[tile]
        tilesUpdated = list()
        for group in groupList:
            tilesUpdated.extend(group.removeFromRestOfGroup(tile))
        return list(set(tilesUpdated))

    def updateAffectedTiles(self, tilesUpdated):
        affectedTiles = list(set(tilesUpdated))
        while len(affectedTiles) > 0:
            affectedTiles.extend(self.updateGroupsByTile(affectedTiles[0]))
            del affectedTiles[0]
            affectedTiles = list(set(affectedTiles))

    def useGroupsToRefine(self):
        tilesUpdated = list()
        for row in range(0, 3):
            for column in range(0, 3):
                driverGroup = self.blockGroup[row][column]
                checkGroups = list()
                checkGroups.append(self.blockGroup[row][(column + 1) % 3])
                checkGroups.append(self.blockGroup[row][(column + 2) % 3])
                solvedTiles = list()
                for group in checkGroups:
                    solvedTiles.extend(group.getSolvedTiles())
                solvedTilesMap = dict()
                for x in range(1, 10):
                    solvedTilesMap[x] = list()
                for solvedTile in solvedTiles:
                    value = solvedTile.getPossibleValues()[0]
                    solvedTilesMap[value].append(solvedTile)
                for tileValue in solvedTilesMap.keys():
                    if len(solvedTilesMap[tileValue]) == 2:
                        rowSet = [1, 2, 3]
                        rowSet.remove(((solvedTilesMap[tileValue][0].getTileRow() - 1) % 3) + 1)
                        rowSet.remove(((solvedTilesMap[tileValue][1].getTileRow() - 1) % 3) + 1)
                        tilesUpdated.extend(driverGroup.findOnlyValueForRow(tileValue, (row * (rowSet[0] - 1) + rowSet[0]), column))
                checkGroups = list()
                checkGroups.append(self.blockGroup[(row + 1) % 3][column])
                checkGroups.append(self.blockGroup[(row + 2) % 3][column])
                solvedTiles = list()
                for group in checkGroups:
                    solvedTiles.extend(group.getSolvedTiles())
                solvedTilesMap = dict()
                for x in range(1, 10):
                    solvedTilesMap[x] = list()
                for solvedTile in solvedTiles:
                    value = solvedTile.getPossibleValues()[0]
                    solvedTilesMap[value].append(solvedTile)
                for tileValue in solvedTilesMap.keys():
                    if len(solvedTilesMap[tileValue]) == 2:
                        columnSet = [1, 2, 3]
                        columnSet.remove(((solvedTilesMap[tileValue][0].getTileColumn() - 1) % 3) + 1)
                        columnSet.remove(((solvedTilesMap[tileValue][1].getTileColumn() - 1) % 3) + 1)
                        tilesUpdated.extend(driverGroup.findOnlyValueForColumn(tileValue, (column * (columnSet[0] - 1) + columnSet[0]), row))
        self.updateAffectedTiles(tilesUpdated)       
    
    def isSolved(self):
        return 'x' not in self.getBoardString() 
    
    def getBoardString(self):
        strToReturn = ''
        for row in range(0, 9):
            for column in range(0, 9):
                tile = self.board[row][column]
                value = 'x'
                if tile.isTileSolved():
                    value = tile.getPossibleValues()[0]
                strToReturn = strToReturn + str(value)   
            strToReturn = strToReturn + '\n'
        return strToReturn
    
    def getColumnPossibleValues(self):
        strToReturn = ''
        for row in range(0, 9):
            for column in range(0, 9):
                tile = self.board[row][column]
                strToReturn = strToReturn + 'row=' + str(row + 1) 
                strToReturn = strToReturn + ';column=' + str(column + 1)
                strToReturn = strToReturn + ':' + str(tile.getPossibleValues())   
                strToReturn = strToReturn + '\n'
        return strToReturn

class Group:
    def __init__(self):
        self.tiles = list()
        
    def findOnlyValueForSeries(self, value, shouldAppend):
        rowList = list()
        for tile in self.tiles:
            if shouldAppend:
                rowList.append(tile)
        if len(rowList) == 1:
            rowList[0].setTileToValue(value)
            return rowList
        else:
            return list()
    
    def findOnlyValueForRow(self, value, row, groupColumnOffset):
        def shouldAppendMethod(tile):
            if tile.getTileRow() == row and (tile.getTileColumn() / 3) == groupColumnOffset and value in tile.getPossibleValues():
                return True
            return False
        return self.findOnlyValueForSeries(value, shouldAppendMethod)

    def findOnlyValueForColumn(self, value, column, groupRowOffset):
        def shouldAppendMethod(tile):
            if tile.getTileColumn() == column and (tile.getTileRow() / 3) == groupRowOffset and value in tile.getPossibleValues():
                return True
            return False
        return self.findOnlyValueForSeries(value, shouldAppendMethod)
        
    def findOnlyOneInGroup(self):
        possiblesCount = dict()
        listOfAffectedTiles = list()
        for x in range(1, 10):
            possiblesCount[x] = list()
        for tile in self.tiles:
            for possibles in tile.getPossibleValues():
                possiblesCount[possibles].append(tile)
        for key in range(1, 10):
            if len(possiblesCount[key]) == 1:
                tile = possiblesCount[key][0]
                if tile.isTileSolved() == False:
                    tile.setTileToValue(key)
                    listOfAffectedTiles.append(tile)
        return listOfAffectedTiles
    
    def removeFromRestOfGroup(self, solvedTile):
        listOfAffectedTiles = list()
        if solvedTile.isTileSolved():
            value = solvedTile.getPossibleValues()[0]
            for tile in self.tiles:
                if tile != solvedTile and tile.removeValueFromPossibleValues(value):
                    listOfAffectedTiles.append(tile)
        return list(set(listOfAffectedTiles))
    
    def applyRuleOfK(self):
        mapOfPossiblesToTiles = dict()
        listOfAffectedTiles = list()
        for tile in self.tiles:
            if tile.isTileSolved() == False:
                possibleValues = tile.getPossibleValues()
                possiblesForTile = self.getStringForList(possibleValues)
                key = mapOfPossiblesToTiles.get(possiblesForTile)
                if key == None:
                    mapOfPossiblesToTiles[possiblesForTile] = list()
                mapOfPossiblesToTiles[possiblesForTile].append(tile)
        myKeys = mapOfPossiblesToTiles.keys()
        myKeys = list(myKeys)
        for keys in myKeys:
            tilesList = mapOfPossiblesToTiles[keys]
            keysList = keys.split(',')
            if tilesList != None and keysList != None and len(tilesList) == len(keysList):
                for value in keysList:
                    for tile in (set(self.tiles).difference(tilesList)):
                        tile.removeValueFromPossibleValues(int(value))
                        listOfAffectedTiles.append(tile)
        return listOfAffectedTiles
        
    def addTileToGroup(self, tile):
        self.tiles.append(tile)
    
    def getSolvedTiles(self):
        solvedTiles = list()
        for tile in self.tiles:
            if tile.isTileSolved():
                solvedTiles.append(tile)
        return solvedTiles
    
    def getStringForList(self, list):
        listString = ''
        for tuple in list:
            if listString != '':
                listString += ','
            listString += str(tuple)
        return listString
