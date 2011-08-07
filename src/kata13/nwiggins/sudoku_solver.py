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
        self.possibleValues = range(1, 10)
        self.row = row
        self.column = column
        
    def removeValueFromPossibleValues(self, value):
        if value in self.possibleValues:
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
        for row, line in enumerate(lines):
            for column, valueString in enumerate(line):
                if valueString != 'x':
                    value = int(valueString)
                    tile = self.board[row][column]
                    tile.setTileToValue(value)
                    affectedTiles = self.updateGroupsByTile(tile)
                    while affectedTiles:
                        affectedTiles |= self.updateGroupsByTile(affectedTiles.pop())

    def updateGroupTilesOnFunction(self, applyRule):
        tilesUpdated = set()
        for row in self.board:
            for tile in row:
                groupList = self.tileToGroups[tile]
                for group in groupList:
                    tilesUpdated |= applyRule(group)
        self.updateAffectedTiles(tilesUpdated)
            
    def findUniqueValues(self):
        def applyRule(group):
            return group.findOnlyOneInGroup()
        self.updateGroupTilesOnFunction(applyRule)
    
    def ruleOfK(self):
        def applyRule(group):
            return group.applyRuleOfK()
        self.updateGroupTilesOnFunction(applyRule)
            
    def updateGroupsByTile(self, tile):
        groupList = self.tileToGroups[tile]
        tilesUpdated = set()
        for group in groupList:
            tilesUpdated |= group.removeFromRestOfGroup(tile)
        return tilesUpdated

    def updateAffectedTiles(self, tilesUpdated):
        affectedTiles = tilesUpdated
        while affectedTiles:
            affectedTiles |= (self.updateGroupsByTile(affectedTiles.pop()))
            
    def useGroupsToRefine(self):
        tilesUpdated = set()
        for rowIndex, row in enumerate(self.blockGroup):
            for columnIndex, driverGroup in enumerate(row):
                checkGroups = list()
                checkGroups.append(row[(columnIndex + 1) % 3])
                checkGroups.append(row[(columnIndex + 2) % 3])
                solvedTilesForValue = dict()
                for group in checkGroups:
                    for solvedTile in group.getSolvedTiles():
                        value = solvedTile.getPossibleValues()[0]
                        solvedTilesForValue.setdefault(value, list()).append(solvedTile)
                for tileValue, solvedTiles in solvedTilesForValue.items():
                    if len(solvedTiles) == 2:
                        rowSet = range(1, 4)
                        rowSet.remove(((solvedTiles[0].getTileRow() - 1) % 3) + 1)
                        rowSet.remove(((solvedTiles[1].getTileRow() - 1) % 3) + 1)
                        tilesUpdated |= driverGroup.findOnlyValueForRow(tileValue, (rowIndex * (rowSet[0] - 1) + rowSet[0]), columnIndex)
                checkGroups = list()
                checkGroups.append(self.blockGroup[(rowIndex + 1) % 3][columnIndex])
                checkGroups.append(self.blockGroup[(rowIndex + 2) % 3][columnIndex])
                solvedTilesForValue = dict()
                for group in checkGroups:
                    for solvedTile in group.getSolvedTiles():
                        value = solvedTile.getPossibleValues()[0]
                        solvedTilesForValue.setdefault(value, list()).append(solvedTile)
                for tileValue, solvedTiles in solvedTilesForValue.items():
                    if len(solvedTiles) == 2:
                        columnSet = range(1, 4)
                        columnSet.remove(((solvedTiles[0].getTileColumn() - 1) % 3) + 1)
                        columnSet.remove(((solvedTiles[1].getTileColumn() - 1) % 3) + 1)
                        tilesUpdated |= driverGroup.findOnlyValueForColumn(tileValue, (columnIndex * (columnSet[0] - 1) + columnSet[0]), rowIndex)
        self.updateAffectedTiles(tilesUpdated)       
    
    def isSolved(self):
        return 'x' not in self.getBoardString() 
    
    def getBoardString(self):
        strToReturn = ''
        for row in self.board:
            for tile in row:
                value = str(tile.getPossibleValues()[0]) if tile.isTileSolved() else 'x'
                strToReturn += value   
            strToReturn += '\n'
        return strToReturn
    
    def getColumnPossibleValues(self):
        strToReturn = ''
        for rowIndex, row in enumerate(self.board):
            for columnIndex, tile in enumerate(row):
                strToReturn += 'row=%(rowNumber)d;column=%(columnNumber)d:%(possibleValues)s\n' % \
                            {"rowNumber": rowIndex + 1, "columnNumber": columnIndex + 1, "possibleValues":tile.getPossibleValues()}
        return strToReturn

class Group:
    
    def __init__(self):
        self.tiles = list()
        
    def findOnlyValueForSeries(self, value, shouldAppend):
        returnSet = set()
        for tile in self.tiles:
            if shouldAppend(tile):
                returnSet.add(tile)
            if len(returnSet) > 1:
                return set()
        for tile in returnSet:
            tile.setTileToValue(value)
        return returnSet
    
    def findOnlyValueForRow(self, value, row, groupColumnOffset):
        def shouldAppendMethod(tile):
            return tile.getTileRow() == row and (tile.getTileColumn() / 3) == groupColumnOffset and value in tile.getPossibleValues()
        return self.findOnlyValueForSeries(value, shouldAppendMethod)

    def findOnlyValueForColumn(self, value, column, groupRowOffset):
        def shouldAppendMethod(tile):
            return tile.getTileColumn() == column and (tile.getTileRow() / 3) == groupRowOffset and value in tile.getPossibleValues()
        return self.findOnlyValueForSeries(value, shouldAppendMethod)
        
    def findOnlyOneInGroup(self):
        possibleValues = dict()
        affectedTiles = set()
        for tile in self.tiles:
            for possibles in tile.getPossibleValues():
                possibleValues.setdefault(possibles, list()).append(tile)
        for key, possible in possibleValues.items():
            if len(possible) == 1:
                tile = possible[0]
                if not tile.isTileSolved():
                    tile.setTileToValue(key)
                    affectedTiles.add(tile)
        return affectedTiles
    
    def removeFromRestOfGroup(self, driverTile):
        listOfAffectedTiles = set()
        if driverTile.isTileSolved():
            value = driverTile.getPossibleValues()[0]
            for tile in self.tiles:
                if tile != driverTile and tile.removeValueFromPossibleValues(value):
                    listOfAffectedTiles.add(tile)
        return listOfAffectedTiles
    
    def applyRuleOfK(self):
        possibilitiesForTiles = dict()
        listOfAffectedTiles = set()
        for tile in self.tiles:
            if not tile.isTileSolved():
                possibleValues = tile.getPossibleValues()
                possibilitiesForTile = self.getStringForList(possibleValues)
                possibilitiesForTiles.setdefault(possibilitiesForTile, set()).add(tile)
        for key, tilesList in possibilitiesForTiles.items():
            keysList = key.split(',')
            if tilesList and keysList and len(tilesList) == len(keysList):
                for value in keysList:
                    value = int(value)
                    for tile in (set(self.tiles) - tilesList):
                        tile.removeValueFromPossibleValues(value)
                        listOfAffectedTiles.add(tile)
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
        listString = None
        for tuple in list:
            if not listString:
                listString = str(tuple)
            else:
                listString = ','.join([listString, str(tuple)])
        return listString
