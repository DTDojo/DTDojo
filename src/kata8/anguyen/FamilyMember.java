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

import java.util.ArrayList;
import java.util.List;


public class FamilyMember {
    private String name;
    private int level;
    private FamilyMember parent;
    private List<FamilyMember> children;
    
    public FamilyMember(String name, int level) {
        this.name = name;
        this.level = level;
        this.children = new ArrayList<FamilyMember>();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        for (FamilyMember child : children) {
            child.setLevel(level + 1);
        }
    }

    public FamilyMember getParent() {
        return parent;
    }

    public void setParent(FamilyMember parent) {
        this.parent = parent;
    }
    
    public void addChild(FamilyMember child) {
        children.add(child);
    }

    public FamilyMember getAncestorAtLevel(int targetLevel) {
        FamilyMember currentParent = parent;
        while ((currentParent != null) && (currentParent.getLevel() > targetLevel)) {
            currentParent = currentParent.getParent();
        }
        
        return currentParent;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        FamilyMember other = (FamilyMember) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        
        return true;
    }
    
    
    
}
