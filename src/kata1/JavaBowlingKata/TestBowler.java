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

import junit.framework.TestCase;

public class TestBowler extends TestCase {
    public void testPerfectGame() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 12; i++) {
            andrew.roll(10);
        }
        
        int total = andrew.getTotal();
        assertEquals(300, total);
    }
    
    public void testWorstGame() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 20; i++) {
            andrew.roll(0);
        }
        
        int total = andrew.getTotal();
        assertEquals(0, total);
    }
    
    public void testMediocreGame() {
        Bowler andrew = new Bowler();
        
        andrew.roll(2);
        andrew.roll(3);
        
        andrew.roll(4);
        andrew.roll(3);
        
        andrew.roll(10);
        
        andrew.roll(3);
        andrew.roll(7);
        
        andrew.roll(5);
        andrew.roll(5);
        
        andrew.roll(6);
        andrew.roll(2);
        
        andrew.roll(3);
        andrew.roll(5);
        
        andrew.roll(0);
        andrew.roll(3);
        
        andrew.roll(8);
        andrew.roll(0);
        
        andrew.roll(10);
        
        andrew.roll(3);
        andrew.roll(5);        
        
        int total = andrew.getTotal();
        assertEquals(108, total);
    }
    
    public void testIncompleteGame() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 10; i++) {
            andrew.roll(10);
        }
        
        int total = andrew.getTotal();
        assertEquals(270, total);
    }
    
    public void testSpareGame() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 10; i++) {
            andrew.roll(0);
            andrew.roll(10);
        }
        
        andrew.roll(2);
        
        int total = andrew.getTotal();
        assertEquals(102, total);
    }
    
    public void testSpareGame2() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 10; i++) {
            andrew.roll(1);
            andrew.roll(9);
        }
        
        andrew.roll(1);
        
        int total = andrew.getTotal();
        assertEquals(110, total);
    }
    
    public void testSpareGame3() {
        Bowler andrew = new Bowler();
        
        for (int i = 0; i < 10; i++) {
            andrew.roll(9);
            andrew.roll(1);
        }
        
        andrew.roll(9);
        
        int total = andrew.getTotal();
        assertEquals(190, total);
    }
}
