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

public class BonusFrame extends Frame {
    
    @Override
    public boolean isStrike() {
        return false;
    }
    
    @Override
    public boolean isSpare() {
        return false;
    }
    
    @Override
    protected int getMaxNumberOfRolls() {
        return 1;
    }
}
