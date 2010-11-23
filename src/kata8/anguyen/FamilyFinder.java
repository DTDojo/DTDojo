/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Nov 21, 2010
*
* @author Andrew Nguyen
*/ 

package com.demandtec.codingdojo.FamilyFinderKata;

import java.util.HashMap;
import java.util.Map;

//Assumes each child has one parent for simplicity's sake.
public class FamilyFinder {
    Map<String, FamilyMember> nameToFamilyMemberMap;
    
    public FamilyFinder() {
        nameToFamilyMemberMap = new HashMap<String, FamilyMember>();
    }
    
    public void addParentChild(String parentName, String childName) {
        FamilyMember parent = getFamilyMember(parentName);
        FamilyMember child = getFamilyMember(childName);
        parent.addChild(child);
        child.setParent(parent);
        child.setLevel(parent.getLevel() + 1);
    }
    
    public String getRelationship(String firstPersonName, String secondPersonName) {
        FamilyMember firstPerson = nameToFamilyMemberMap.get(firstPersonName);
        FamilyMember secondPerson = nameToFamilyMemberMap.get(secondPersonName);       
        
        if (firstPerson == null || secondPerson == null) {
            return "STRANGER";
        }
        
        FamilyMember sharedAncestor = getSharedAncestor(firstPerson, secondPerson);
        
        if (sharedAncestor == null) {
            return "STRANGER";
        }
        
        if(firstPerson.equals(sharedAncestor)) {
            if (secondPerson.getLevel() == sharedAncestor.getLevel() + 1) {
                return "PARENT";
            }
            
            if (secondPerson.getLevel() == sharedAncestor.getLevel() + 2) {
                return "GRANDPARENT";
            }
        }
        
        if (secondPerson.equals(sharedAncestor)) {
            if (firstPerson.getLevel() == sharedAncestor.getLevel() + 1) {
                return "CHILD";
            }
            
            if (firstPerson.getLevel() == sharedAncestor.getLevel() + 2) {
                return "GRANDCHILD";
            }
        }
        
        if (firstPerson.getLevel() == secondPerson.getLevel()) {
            if (sharedAncestor.getLevel() == firstPerson.getLevel() - 1) {
                return "SIBLING";
            } else if (sharedAncestor.getLevel() == firstPerson.getLevel() - 2) {
                return "COUSIN";
            }
        }
        
        if ((firstPerson.getLevel() == secondPerson.getLevel() + 1) && firstPerson.getLevel() == sharedAncestor.getLevel() + 2) {
            return "NEPHEW/NIECE";
        }
        
        if ((secondPerson.getLevel() == firstPerson.getLevel() + 1) && secondPerson.getLevel() == sharedAncestor.getLevel() + 2) {
            return "UNCLE/AUNT";
        }
        
        return "RELATED";
    }
    
    private FamilyMember getSharedAncestor(FamilyMember firstPerson, FamilyMember secondPerson) {
        if (firstPerson.getLevel() < secondPerson.getLevel()) {
            secondPerson = secondPerson.getAncestorAtLevel(firstPerson.getLevel());
        } else if (firstPerson.getLevel() > secondPerson.getLevel()) {
            firstPerson = firstPerson.getAncestorAtLevel(secondPerson.getLevel());
        }
        
        while (firstPerson != null && secondPerson != null && !firstPerson.equals(secondPerson)) {
            firstPerson = firstPerson.getParent();
            secondPerson = secondPerson.getParent();
        }
        
        if (firstPerson == null || secondPerson == null) {
            return null;
        }
        
        return firstPerson;
    }

    private FamilyMember getFamilyMember(String name) {
        FamilyMember familyMember = nameToFamilyMemberMap.get(name);
        if (familyMember == null) {
            familyMember = new FamilyMember(name, 0);
            nameToFamilyMemberMap.put(name, familyMember);
        }
        
        return familyMember;
    }
}
