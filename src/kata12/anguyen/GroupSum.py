import itertools
class GroupSum:
    
    @staticmethod
    def groupSum(array, target):
        if (array == None) or (array == []):
            return []
        
        array.sort()
        
        combinations = []
        for lengthOfCombination in range(1, len(array) + 1):
            combinationsWithDuplicates = itertools.combinations(array, lengthOfCombination)
            #Remove duplicates by converting the list into a set.
            combinations += list(set(combinationsWithDuplicates))
			
        combinations.sort()
        
        solutions = []
        for combination in combinations:
            if sum(combination) == target:
                solutions += [combination]
                
        return solutions