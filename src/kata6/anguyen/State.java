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

import java.util.HashMap;
import java.util.Map;

public class State {
    String name;
    String value;
    Map<String, State> transitions;
    
    public State(String nameValuePair) {
        String[] splitNameValuePair = nameValuePair.split(":");
        this.name = splitNameValuePair[0];
        this.value = splitNameValuePair[1];
        transitions = new HashMap<String, State>();
    }
    
    public void addTransition(String symbol, State destination) {
        transitions.put(symbol, destination);
    }
    
    public State getDestinationState(String transition) {
        State destination = transitions.get(transition);
       
        if (destination == null) {
            destination = this;
        }
        
        return destination;
    }
    
    public String getValue() {
        return value;
    }
}
