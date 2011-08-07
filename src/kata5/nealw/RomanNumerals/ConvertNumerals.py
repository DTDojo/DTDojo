'''
Created on Nov 1, 2010

@author: nwiggins
'''

class ConvertNumerals:

    def __init__(self):
        self.mapping = {1000:'M', 500:'D', 100:'C', 50:'L',  10:'X', 5:'V', 1:'I'}
       
    def toRomanNumerals(self, number):
        stringToReturn = ''
        if number > 3999:
            return 'Not Valid'
        stringToReturn += self.mapping[1000] * (number / 1000)
        number = number % 1000
        myKeys = self.mapping.keys()
        myKeys.sort()
        for x in range(len(myKeys) - 2, -1, -1):
            currentKey = myKeys[x]
            if  number / currentKey > 0:
                if currentKey * 2  == myKeys[x + 1]:
                    if number / (currentKey * 2 - myKeys[x - 1]) == 1:
                        stringToReturn += self.mapping[myKeys[x - 1]] + self.mapping[myKeys[x + 1]]
                        number = number % (currentKey* 2 - myKeys[x - 1])
                    else:
                        stringToReturn += self.mapping[currentKey]
                        tempNumber = number % currentKey 
                        stringToReturn += self.mapping[myKeys[x - 1]] * (tempNumber / myKeys[x - 1])
                        number = number % (myKeys[x] + myKeys[x - 1] * 3)
                        number = number % (myKeys[x] + myKeys[x - 1] * 2) 
                        number = number % (myKeys[x] + myKeys[x - 1]) 
                        number = number % (myKeys[x])
                else:
                    if number / (myKeys[x + 1] - currentKey) > 0:
                        stringToReturn += self.mapping[currentKey] + self.mapping[myKeys[x + 1]]
                        number = number % (myKeys[x + 1] - currentKey)
                    else:
                        stringToReturn += self.mapping[currentKey] *(number / currentKey)
                        number = number % currentKey
        return stringToReturn
        