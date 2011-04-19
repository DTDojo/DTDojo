'''
Created on Apr 9, 2011

@author: nwiggins
'''
class GroupSum(object):
    def groupSum(self, numberArray, sum):
        primaryNumbers = sorted(numberArray, reverse=True)
        arrayLength = len(primaryNumbers)
        secondaryNumbers = []
        for x in range(arrayLength-1,-1,-1):
            currentValue = primaryNumbers[x]
            if currentValue < 0:
                secondaryNumbers.append(currentValue)
                del primaryNumbers[x]
            elif currentValue >= 0:
                break
        if sum < 0:
            secondaryNumbers, primaryNumbers = primaryNumbers,secondaryNumbers
        for x in range(0,len(primaryNumbers)):
            if primaryNumbers[x] == sum:
                currentList = []
                currentList.append(primaryNumbers[x])
                return currentList
            elif primaryNumbers[x] < sum:
                subList = self.groupSum(primaryNumbers[x+1:],sum-primaryNumbers[x])
                if subList != None:
                    subList.append(primaryNumbers[x])
                    return subList
        for x in range(0, len(secondaryNumbers)):
            currentNegative = secondaryNumbers[x]
            negativeList = secondaryNumbers[x:]
            negativeList.extend(primaryNumbers)
            subList = self.groupSum(negativeList, sum-currentNegative)
            if subList != None:
                subList.append(currentNegative)
                return subList
        return None
