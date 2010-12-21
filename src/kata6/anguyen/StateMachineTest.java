/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Nov 4, 2010
*
* @author Andrew Nguyen
*/ 

package com.demandtec.codingdojo.FSMKata;

import junit.framework.TestCase;

public class StateMachineTest extends TestCase {

    public void testEvenConsecutiveOneFsm() {
        StateMachine fsm = new StateMachine("0,1",  "EVEN:pass,ODD:fail,BAD:fail",  "EVEN:ODD:1,ODD:EVEN:1,ODD:BAD:0");
        assertEquals("pass", fsm.execute("00110"));
        fsm.reset(); // put it back into initial state
        assertEquals("fail", fsm.execute("00111"));
        fsm.reset();
        assertEquals("fail", fsm.execute("001110000110011"));
    }
}
