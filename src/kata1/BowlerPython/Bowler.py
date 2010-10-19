'''
Created on Oct 2, 2010

@author: nwiggins
'''
class Bowler(object):
    
    def __init__(self):
        self._roll = []
        self._frame = 0
        self._frame_roll = 0
        self._bonusRolls = 0
        
    def rolls(self,scoreOnRoll):
        if self._frame == 10 and self._bonusRolls ==0:
            raise AssertionError("Invalid Roll")
        elif self._bonusRolls >0:
            self._bonusRolls -= 1
        elif scoreOnRoll == 10 and self._frame_roll == 0:
            self._frame +=1
            if self._frame == 10:
                self._bonusRolls =2
        elif scoreOnRoll > 10 or (self._frame_roll >0 and (scoreOnRoll + self._roll[len(self._roll)-1])>10):
            raise AssertionError("Invalid Roll")
        elif self._frame_roll >=1:
            self._frame += 1
            self._frame_roll = 0
            if self._frame == 10 and scoreOnRoll+self._roll[self._roll.__len__()-1]==10:
                self._bonusRolls =1
        else:
            self._frame_roll +=1    
        self._roll.append(scoreOnRoll)
        
    def currentScore(self):
        score = 0
        numberOfRolls = self._roll.__len__()
        frameRoll = 0
        for i in range(self._roll.__len__()):
            a = self._roll[i]
            frameRoll+=1
            if a == 10:
                if i+2 < numberOfRolls:
                    score += a + self._roll[i+1] + self._roll[i+2]
                frameRoll=0
            elif frameRoll == 2 and (self._roll[i-1] + self._roll[i] == 10):
                if i+1 < numberOfRolls:
                    score += self._roll[i-1] +a + self._roll[i+1]
                frameRoll=0
            elif frameRoll == 2:
                score += self._roll[i-1] + a
                frameRoll=0
        return score
    

    
    
    