'''
Created on Apr 9, 2011

@author: nwiggins
'''

class SudokuSolver:
    def solve(self,fileName): 
        file = open(fileName)
        string = file.read()
        self.board = Board()
        self.board.setKnownValues(string)
        self.board.findUniqueValues()
    def printBoard(self):
        return self.board.getBoardString()
        
class Tile:
    def __init__(self):
        self.possibleValues = [1,2,3,4,5,6,7,8,9]
        
    def removeValueFromPossibleValues(self,value):
        if self.possibleValues.count(value) >0:
            self.possibleValues.remove(value) 
            return True
        else:
            return False
    
    def isTileSolved(self):
        return len(self.possibleValues) == 1
    
    def setTileToValue(self,value):
        self.possibleValues = list()
        self.possibleValues.append(value)
        
    def getPossibleValues(self):
        return self.possibleValues

class Board:
    def __init__(self):
        self.board = list()
        self.tileToGroups = dict()
        rowGroup = list()
        columnGroup = list()
        blockGroup = list()
        for row in range(0,9):
            self.board.append(list())
            rowGroup.append(Group())
            if len(blockGroup) <= (row/3):
                blockGroup.append(list())
            for column in range(0,9):
                if len(columnGroup) <= column:
                    columnGroup.append(Group())
                if len(blockGroup[row/3]) <= (column/3):
                    blockGroup[row/3].append(Group())
                currentTile = Tile()
                self.board[row].append(currentTile)
                groupList = list()
                groupList.append(rowGroup[row])
                groupList.append(columnGroup[column])
                groupList.append(blockGroup[row/3][column/3])
                for group in groupList:
                    group.addTileToGroup(currentTile)
                self.tileToGroups[currentTile] = groupList
                
    def setKnownValues(self,knownValues):
        lines = knownValues.splitlines()
        for row in range(0,9):
            line = lines[row]
            for column in range(0,9):
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

    def findUniqueValues(self):
        tilesUpdated = list()
        for row in range(0,9):
            for column in range(0,9):
                tile = self.board[row][column]
                groupList = self.tileToGroups[tile]
                for group in groupList:
                    tilesUpdated.extend(group.findOnlyOneInGroup())
        affectedTiles = list(set(tilesUpdated))
        while len(affectedTiles) >0:
            affectedTiles.extend(self.updateGroupsByTile(affectedTiles[0]))
            del affectedTiles[0]
            affectedTiles = list(set(affectedTiles))
            
    def updateGroupsByTile(self,tile):
        groupList = self.tileToGroups[tile]
        tilesUpdated = list()
        for group in groupList:
            tilesUpdated.extend(group.removeFromRestOfGroup(tile))
        return list(set(tilesUpdated))
     
    def getBoardString(self):
        strToReturn = ''
        for row in range(0,9):
            for column in range(0,9):
                tile = self.board[row][column]
                value = 'x'
                if tile.isTileSolved():
                    value = tile.getPossibleValues()[0]
                strToReturn = strToReturn + str(value)   
            strToReturn = strToReturn + '\n'
        for row in range(0,9):
            for column in range(0,9):
                tile = self.board[row][column]
                strToReturn = strToReturn +'row=' + str(row+1) + 'col='+str(column+1)+':'+str(tile.getPossibleValues())   
                strToReturn = strToReturn + '\n'
        return strToReturn

class Group:
    def __init__(self):
        self.tiles = list()
        
    def findOnlyOneInGroup(self):
        possiblesCount = dict()
        listOfAffectedTiles = list()
        for x in range(1,10):
            possiblesCount[x] = list()
        for tile in self.tiles:
            for possibles in tile.getPossibleValues():
                possiblesCount[possibles].append(tile)
        for key in range(1,10):
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
                if tile != solvedTile:
                    isAffected = tile.removeValueFromPossibleValues(value)
                    if isAffected:
                        listOfAffectedTiles.append(tile)
                    
        return list(set(listOfAffectedTiles))
        
    def addTileToGroup(self,tile):
        self.tiles.append(tile)