'''
Created on Oct 2, 2010

@author: nwiggins
'''
class Bowler:
    
    def __init__(self):
        self.roll = []
        self.frame = 0
        self.frame_roll = 0
        self.bonusRolls = 0
        
    def rolls(self, scoreOnRoll):
        if self.frame == 10 and self.bonusRolls == 0 or scoreOnRoll > 10 \
            or (self.frame_roll > 0 and (scoreOnRoll + self.roll[len(self.roll)-1]) > 10):
            raise AssertionError("Invalid Roll")
        elif self.bonusRolls > 0:
            self.bonusRolls -= 1
        elif scoreOnRoll == 10 and self.frame_roll == 0:
            self.frame += 1
            if self.frame == 10:
                self.bonusRolls = 2
        elif self.frame_roll >= 1:
            self.frame += 1
            self.frame_roll = 0
            if self.frame == 10 and (scoreOnRoll + self.roll[len(self.roll) - 1] == 10) :
                self.bonusRolls = 1
        else:
            self.frame_roll += 1    
        self.roll.append(scoreOnRoll)
        
    def currentScore(self):
        score = 0
        frameRoll = 0
        numberOfRolls = len(self.roll)
        for i in range(len(self.roll)):
            a = self.roll[i]
            frameRoll += 1
            if a == 10:
                if i + 2 < numberOfRolls:
                    score += a + self.roll[i + 1] + self.roll[i + 2]
                frameRoll = 0
            elif frameRoll == 2 and (self.roll[i - 1] + self.roll[i] == 10):
                if i + 1 < numberOfRolls:
                    score += self.roll[i - 1] + a + self.roll[i + 1]
                frameRoll = 0
            elif frameRoll == 2:
                score += self.roll[i - 1] + a
                frameRoll = 0
        return score
    