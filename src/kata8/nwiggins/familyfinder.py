'''
Created on Nov 21, 2010

@author: nwiggins
'''

class FamilyTree:

    def __init__(self):
        self.familyMemberNodeByName = dict()
        self.rootNode = list()
    
    def add_parent_child(self,parent,child):
        parentNode = self.familyMemberNodeByName[parent] if parent in self.familyMemberNodeByName else None
        childNode = self.familyMemberNodeByName[child] if child in self.familyMemberNodeByName else None
        if not parentNode:
            parentNode = FamilyMemberNode(parent)
            self.familyMemberNodeByName[parent] = parentNode
        if not childNode:
            childNode = FamilyMemberNode(child)
            self.familyMemberNodeByName[child] = childNode
        parentNode.setChild(childNode)
        if not childNode.getLevelFromNode():
            if childNode in self.rootNode:
                self.rootNode.remove(childNode)
            self.rootNode.append(parentNode)
        childNode.setParent(parentNode)
    
    def relationship(self,person, personToRelate): 
        returner = "STRANGER"
        personNode = self.familyMemberNodeByName[person] if person in self.familyMemberNodeByName else None
        personToRelateNode =  self.familyMemberNodeByName[personToRelate] if personToRelate in self.familyMemberNodeByName else None
        if not personNode or not personToRelateNode:
            return returner
        returner = "RELATED"
        level = personNode.getLevelFromNode() - personToRelateNode.getLevelFromNode()
        if not level:
            if self.isSibling(personNode,personToRelateNode):
                return "SIBLING"
            elif self.isCousin(personNode, personToRelateNode):
                return "COUSIN"
        elif level is 1:
            if self.isChild(personNode,personToRelateNode):
                return "CHILD"
            elif self.isNephewNiece(personNode, personToRelateNode):
                return "NEPHEW/NIECE"
        elif level is -1:
            if self.isChild(personToRelateNode, personNode):
                return "PARENT"
            elif self.isNephewNiece(personToRelateNode, personNode):
                return "UNCLE/AUNT"
        elif level is 2:
            if self.isGrandChild(personNode, personToRelateNode):
                return "GRANDCHILD"
        elif level is -2:
            if self.isGrandChild(personToRelateNode, personNode):
                return "GRANDPARENT"
        return returner

    def isChild(self,person,possibleParent):
        return possibleParent in person.parentNode
    
    def isSibling(self, person,possibleSibling):
        parent = person.parentNode[0]
        return parent in possibleSibling.parentNode
    
    def isCousin(self,person,possibleCousin):
        for parent in person.parentNode:
            for possibleCousinsParent in possibleCousin.parentNode:
                if self.isSibling(parent, possibleCousinsParent):
                    return True
        return False
    def isNephewNiece(self, person,possibleAuntUncle):
        for possibleSibling in person.parentNode:
            if self.isSibling(possibleAuntUncle, possibleSibling):
                return True
        return False
    
    def isGrandChild(self,person,possibleGrandParent):
        for parent in person.parentNode:
            if possibleGrandParent in parent.parentNode:
                return True
        return False
    
class FamilyMemberNode:
    
    def __init__(self,name):
        self.name = name
        self.levelFromNode = 0
        self.parentNode = list()
        self.childNode = list()
    
    def setChild(self,childNode):
        if len(childNode.parentNode) > 0:
            self.childNode = childNode.parentNode[0].childNode
        self.childNode.append(childNode)
    
    def setParent(self,parentNode):
        if len(parentNode.childNode) > 0:
            self.parentNode = parentNode.childNode[0].parentNode
        self.parentNode.append(parentNode)
        self.levelFromNode = parentNode.getLevelFromNode() + 1
        for child in self.childNode:
            child.setParent(self)
    
    def getLevelFromNode(self):
        return self.levelFromNode