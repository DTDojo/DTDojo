'''
Created on Dec 6, 2010

@author: nwiggins
'''
import random

class Game:

    def __init__(self, suspects, weapons, places):
        self.suspects = suspects
        self.weapons = weapons
        self.places = places
        self.murderer = random.choice(suspects)
        self.murderWeapon = random.choice(weapons)
        self.murderPlace = random.choice(places)
    
    def isIt(self,suspect, weapon, place):
        if suspect != self.murderer:
            return suspect
        elif weapon != self.murderWeapon:
            return weapon
        elif place != self.murderPlace:
            return place
        else: 
            return None
        
        
class Player:
    
    def __init__(self, suspects, weapons, places):
        self.suspects = suspects
        self.weapons = weapons
        self.places = places
        self.murderer = 0
        self.murderWeapon = 0
        self.murderPlace = 0
    
    def solve(self, game):
        nextClue = ''
        while nextClue:
            currentSuspect = self.suspects[self.murderer]
            currentWeapon = self.weapons[self.murderWeapon]
            currentPlace = self.places[self.murderPlace]
            nextClue = game.isIt(currentSuspect,currentWeapon, currentPlace)
            if nextClue == currentSuspect:
                self.murderer += 1
            elif nextClue == currentWeapon:
                self.murderWeapon += 1
            elif nextClue == currentPlace:
                self.murderPlace += 1
        return "%s with %s at %s" % (self.suspects[self.murderer], self.weapons[self.murderWeapon], self.places[self.murderPlace])
            