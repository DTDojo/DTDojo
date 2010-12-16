'''
Created on Oct 31, 2010

@author: Andrew
'''

class RomanNumeralConverter(object):
    
    @staticmethod
    def convertNumber(number):
        result = ''
        
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 1000, "M", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 500, "D", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 100, "C", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 50, "L", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 10, "X", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 5, "V", result);
        number,result = RomanNumeralConverter.__calculateRomanNumeral(number, 1, "I", result);        
        
        result = RomanNumeralConverter.__replaceDuplicateTokens(result)
        result = RomanNumeralConverter.__replaceIncorrectTokens(result)
        
        return result;
        
    @staticmethod
    def __calculateRomanNumeral(number, divisor, character, result):
        timesToWrite = number / divisor
        remainder = number % divisor
        
        result += RomanNumeralConverter.__writeResult(character, timesToWrite)
        return remainder, result 
        
    @staticmethod
    def __writeResult(letter, timesToWrite):
        result = [letter] * timesToWrite
        return ''.join(result);
    
    @staticmethod
    def __replaceDuplicateTokens(result):
        result = result.replace("CCCC", "CD")
        result = result.replace("XXXX", "XL")
        result = result.replace("IIII", "IV")
        return result
    
    @staticmethod
    def __replaceIncorrectTokens(result):
        result = result.replace("DCD", "CM")
        result = result.replace("LXL", "XC")
        result = result.replace("VIV", "IX")
        return result

