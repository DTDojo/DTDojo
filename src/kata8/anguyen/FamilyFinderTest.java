/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Nov 22, 2010
*
* @author Andrew Nguyen
*/ 

package com.demandtec.codingdojo.FamilyFinderKata;

import junit.framework.TestCase;

public class FamilyFinderTest extends TestCase {
    
    
    public void testParentAndChild() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Parent1", "Child1");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent2", "Parent2");
        familyTree.addParentChild("Parent2", "Child2");
        
        assertEquals("CHILD", familyTree.getRelationship("Child1", "Parent1"));
        assertEquals("PARENT", familyTree.getRelationship("Parent1", "Child1"));
        assertEquals("CHILD", familyTree.getRelationship("Child2", "Parent2"));
        assertEquals("PARENT", familyTree.getRelationship("Parent2", "Child2"));
        assertEquals("STRANGER", familyTree.getRelationship("Parent2", "Child1"));
        assertEquals("STRANGER", familyTree.getRelationship("Child2", "Parent1"));
    }
    
    public void testGrandparentAndGrandchild() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Parent1", "Child1");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent2", "Parent2");
        familyTree.addParentChild("Parent2", "Child2");
        
        assertEquals("GRANDCHILD", familyTree.getRelationship("Child1", "Grandparent1"));
        assertEquals("GRANDPARENT", familyTree.getRelationship("Grandparent1", "Child1"));
        assertEquals("GRANDCHILD", familyTree.getRelationship("Child2", "Grandparent2"));
        assertEquals("GRANDPARENT", familyTree.getRelationship("Grandparent2", "Child2"));
        assertEquals("STRANGER", familyTree.getRelationship("Grandparent2", "Child1"));
        assertEquals("STRANGER", familyTree.getRelationship("Child1", "Grandparent2"));
    }
    
    public void testSiblingCousin() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Parent1", "Child1-1");
        familyTree.addParentChild("Parent1", "Child1-2");
        familyTree.addParentChild("Parent2", "Child2-1");
        familyTree.addParentChild("Parent2", "Child2-2");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent1", "Parent2");
        familyTree.addParentChild("GreatGrandparent1", "Grandparent1");
        
        assertEquals("SIBLING", familyTree.getRelationship("Child1-1", "Child1-2"));
        assertEquals("SIBLING", familyTree.getRelationship("Child1-2", "Child1-1"));
        assertEquals("SIBLING", familyTree.getRelationship("Child2-1", "Child2-2"));
        assertEquals("SIBLING", familyTree.getRelationship("Child2-2", "Child2-1"));
        assertEquals("COUSIN", familyTree.getRelationship("Child2-2", "Child1-1"));
        assertEquals("COUSIN", familyTree.getRelationship("Child2-2", "Child1-2"));
        assertEquals("COUSIN", familyTree.getRelationship("Child2-1", "Child1-1"));
        assertEquals("COUSIN", familyTree.getRelationship("Child2-1", "Child1-2"));
        assertEquals("COUSIN", familyTree.getRelationship("Child1-2", "Child2-1"));
        assertEquals("COUSIN", familyTree.getRelationship("Child1-2", "Child2-2"));
        assertEquals("COUSIN", familyTree.getRelationship("Child1-1", "Child2-1"));
        assertEquals("COUSIN", familyTree.getRelationship("Child1-1", "Child2-2"));
    }
    
    public void testUncleAuntAndNephewNiece() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Parent1", "Child1-1");
        familyTree.addParentChild("Parent1", "Child1-2");
        familyTree.addParentChild("Parent2", "Child2-1");
        familyTree.addParentChild("Parent2", "Child2-2");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent1", "Parent2");
        
        assertEquals("UNCLE/AUNT", familyTree.getRelationship("Parent1", "Child2-1"));
        assertEquals("UNCLE/AUNT", familyTree.getRelationship("Parent1", "Child2-2"));
        assertEquals("UNCLE/AUNT", familyTree.getRelationship("Parent2", "Child1-1"));
        assertEquals("UNCLE/AUNT", familyTree.getRelationship("Parent2", "Child1-2"));
        assertEquals("NEPHEW/NIECE", familyTree.getRelationship("Child1-1", "Parent2"));
        assertEquals("NEPHEW/NIECE", familyTree.getRelationship("Child1-2", "Parent2"));
        assertEquals("NEPHEW/NIECE", familyTree.getRelationship("Child2-1", "Parent1"));
        assertEquals("NEPHEW/NIECE", familyTree.getRelationship("Child2-2", "Parent1"));
    }
    
    public void testGreatUncleAunt() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Child1-1", "Child1-1-1");
        familyTree.addParentChild("Parent1", "Child1-1");
        familyTree.addParentChild("Parent1", "Child1-2");
        familyTree.addParentChild("Parent2", "Child2-1");
        familyTree.addParentChild("Parent2", "Child2-2");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent1", "Parent2");
        
        assertEquals("RELATED", familyTree.getRelationship("Child1-1-1", "Parent2"));
    }
    
    public void testGreatGrandparent() {
        FamilyFinder familyTree = new FamilyFinder();
        familyTree.addParentChild("Parent1", "Child1-1");
        familyTree.addParentChild("Parent1", "Child1-2");
        familyTree.addParentChild("Parent2", "Child2-1");
        familyTree.addParentChild("Parent2", "Child2-2");
        familyTree.addParentChild("Grandparent1", "Parent1");
        familyTree.addParentChild("Grandparent1", "Parent2");
        familyTree.addParentChild("GreatGrandparent1", "Grandparent1");
        
        assertEquals("RELATED", familyTree.getRelationship("Child1-1", "GreatGrandparent1"));
        assertEquals("RELATED", familyTree.getRelationship("Child2-1", "GreatGrandparent1"));
        assertEquals("RELATED", familyTree.getRelationship("GreatGrandparent1", "Child1-2"));
    }
}
