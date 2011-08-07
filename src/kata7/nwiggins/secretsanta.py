'''
Created on Nov 13, 2010

@author: nwiggins
'''
import random

class Participant(object):

    def __init__(self, givenName, familyName):
        self.givenName = givenName
        self.familyName = familyName
        self.secretSanta = None
    
    def setSecretSanta(self, secretSanta):
        self.secretSanta = secretSanta
        secretSanta.setSecretSantaTo(self)
    
    def setSecretSantaTo(self, participant):
        self.secretSantaTo = participant
        
    def getFamilyName(self):
        return self.familyName
    
    def getName (self):
        return self.givenName + " " + self.familyName
    
class SecretSantaGroup(object):
    
    def getSecretSantaListFromList(self, myList):
        if isinstance(myList, str):
            myList = myList.split(',')
        families = dict()
        numberOfParticipants = len(myList)
        largestFamilySize = 0
        availableSecretSantas = set()
        alreadySecretSantas = set()
        for name in myList:
            givenName, familyName = name.split(' ')
            participant = Participant(givenName, familyName)
            availableSecretSantas.add(participant)
            if familyName not in families:
                families[familyName] = set()
            families[familyName].add(participant)
            if len(families[familyName]) > largestFamilySize:
                largestFamilySize = len(families[familyName])
        if numberOfParticipants / 2 < largestFamilySize:
            raise Exception("Wow there, Hoss. One family's way too big now.")
        for largestFamilySet in families.values(): #familiesValues:
            availableSecretSantaPoolForFam = list(availableSecretSantas.difference(largestFamilySet))
            for participant in largestFamilySet:
                self.chooseSecretSanta(participant, availableSecretSantaPoolForFam, alreadySecretSantas, availableSecretSantas)
        tableString = "\n" + " " * 22 + "person" + " " * 22 + "|" + " " * 19 + "secret santa" + "\n"
        tableString += "_" * 101 + "\n"
        for participant in alreadySecretSantas:
            participantName = participant.getName()
            secretSantaName = participant.secretSanta.getName()
            tableString += " " * ((50 - len(participantName)) / 2)
            tableString += "%s" % participantName
            tableString += " " * (50 - len(participantName) - ((50 - len(participantName)) / 2)) 
            tableString += "|" + " " * ((50 - len(secretSantaName)) / 2) + "%s\n" % secretSantaName
        return tableString
   
    def chooseSecretSanta(self, participant, availableSecretSantaPoolForFam, alreadySecretSantaPool, availableSecretSantas):   
        if participant.secretSanta:
            return   
        if not availableSecretSantaPoolForFam:
            alreadySSPoolList = list(alreadySecretSantaPool)
            rand = random.choice(alreadySSPoolList)
            while(rand.getFamilyName() == participant.getFamilyName()):
                rand = random.choice(alreadySSPoolList)
            replacedParticipant = rand.secretSantaTo
            replacedParticipant.secretSanta = None
            participant.setSecretSanta(rand)
            availPool = list()
            for availSS in availableSecretSantas:
                if availSS.getFamilyName() != replacedParticipant.getFamilyName():
                    availPool.append(availSS)
            self.chooseSecretSanta(replacedParticipant, availPool, alreadySecretSantaPool, availableSecretSantas)
        else:
            secretSanta = random.choice(availableSecretSantaPoolForFam)
            participant.setSecretSanta(secretSanta)
            availableSecretSantaPoolForFam.remove(secretSanta)
            availableSecretSantas.discard(secretSanta)
            alreadySecretSantaPool.add(secretSanta)
