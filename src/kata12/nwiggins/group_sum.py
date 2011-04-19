'''
Created on Apr 9, 2011

@author: nwiggins
'''
class GroupSum(object):
    def groupSum(self, numberArray, sum):
        sortedNumbers = sorted(numberArray, reverse=True)
        arrayLength = len(sortedNumbers)
        negativeNumbers = []
        for x in range(arrayLength-1,0,-1):
            currentValue = sortedNumbers[x]
            if currentValue < 0:
                negativeNumbers.append(currentValue)
                del sortedNumbers[x]
            elif currentValue >= 0:
                break
        for x in range(0,len(sortedNumbers)):
            if sortedNumbers[x] == sum:
                currentList = []
                currentList.append(sortedNumbers[x])
                return currentList
            elif sortedNumbers[x] < sum:
                subList = self.groupSum(sortedNumbers[x+1:],sum-sortedNumbers[x])
                if subList != None:
                    subList.append(sortedNumbers[x])
                    return subList
        for x in range(0, len(negativeNumbers)):
            currentNegative = negativeNumbers[x]
            negativeList = negativeNumbers[x:]
            negativeList.extend(sortedNumbers)
            subList = self.groupSum(negativeList, sum-currentNegative)
            if subList != None:
                subList.append(currentNegative)
                return subList
        return None
