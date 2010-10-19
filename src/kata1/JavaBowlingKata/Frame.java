/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Oct 4, 2010
*
* @author Andrew Nguyen
*/ 

package com.demandtec.codingdojo.BowlingKata;

import java.util.ArrayList;
import java.util.List;

public abstract class Frame {
    protected List<Integer> rolls;

    public Frame() {
        rolls = new ArrayList<Integer>(getMaxNumberOfRolls());
    }
    
    public Integer getRoll(int rollNumber) {
        if (rollNumber >= rolls.size()) {
            return null;
        }
    
        return rolls.get(rollNumber);
    }
    
    public Integer getFirstRoll() {
        return getRoll(0);
    }
    
    public Integer getSecondRoll() {
        return getRoll(1);
    }
    
    public boolean isStrike() {
        if (rolls.isEmpty()) {
            return false;
        }
        
        return (rolls.get(0) == 10);
    }
    
    public boolean isSpare() {
        if (rolls.size() < getMaxNumberOfRolls()) {
            return false;
        }
        
        int firstRoll = rolls.get(0);
        int secondRoll = rolls.get(1);
        
        return ((firstRoll + secondRoll) == 10);
    }
    
    public boolean isComplete() {
        return rolls.size() == getMaxNumberOfRolls() || isStrike();
    }
    
    public void addRoll(int roll) {
        if (rolls.size() == getMaxNumberOfRolls()) {
            throw new IllegalArgumentException("Maximum number of rolls in a frame cannot be more than " + getMaxNumberOfRolls());
        }
        
        rolls.add(roll);
    }
    
    public int getScore() {
        int score = 0;
        
        for (Integer roll : rolls) {
            score += roll;
        }
        
        return score;
    }
    
    protected abstract int getMaxNumberOfRolls();

}
