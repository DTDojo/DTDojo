'''
Created on Nov 4, 2010

@author: nwiggins
'''

class FiniteStateMachine(object):

    def __init__(self, symbols, states, transitions):
        self.acceptableInputs = set(symbols.split(','))
        keepMachines = dict()
        self.headNode = None
        for stateDef in states.split(','):
            stateDefPartials = stateDef.split(':') 
            if len(stateDefPartials) != 2:
                raise Exception('invalid state definition')
            stateName = stateDefPartials[0]
            stateStatus = stateDefPartials[1]
            createMachine = MachineNode(stateName,stateStatus,self.acceptableInputs)
            if self.headNode is None:
                self.headNode = createMachine
            keepMachines.update([(stateName,createMachine)])
        for transitionDef in transitions.split(','):
            transitionDefPartial = transitionDef.split(':')
            if len(transitionDefPartial) != 3:
                raise Exception('invalid transition definition')
            machine = keepMachines[transitionDefPartial[0]]
            targetMachine = keepMachines[transitionDefPartial[1]]
            machine.createMappings(targetMachine,transitionDefPartial[2])
        self.reset()

    def reset(self):
        self.currentNode = self.headNode
    
    def execute(self, executionString):
        for charIndex in range(0,len(executionString)):
            inputCharacter = executionString[charIndex] 
            if inputCharacter not in self.acceptableInputs:
                raise Exception('Invalid input')
            self.currentNode = self.currentNode.getNodeFromInput(inputCharacter)
        return self.currentNode.getStatus()       
    
    
class MachineNode(object):
        def __init__(self, nodeName, nodeStatus, inputs):
            self.nodeName = nodeName
            self.nodeStatus = nodeStatus
            self.map = dict([(inputTuple, self) for inputTuple in inputs])
            
        def createMappings(self, toMachineNode, input):
            if input not in self.map:
                raise Exception('State Does not Exist')
            self.map[input] = toMachineNode
            
        def getNodeFromInput(self, input):
            return self.map[input]
        
        def getStatus(self):
            return self.nodeStatus