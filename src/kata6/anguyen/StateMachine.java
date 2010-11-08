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

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

public class StateMachine {
    private State currentState;
    private LinkedHashMap<String, State> stateNameToState;
    private Set<String> validSymbols;
    
    public StateMachine(String symbols, String states, String transitions) {
        initializeSymbols(symbols);
        initializeStates(states);
        initializeTransitions(transitions);
    }
    
    public void reset() {
        currentState = stateNameToState.values().iterator().next();
    }
    
    public String execute(String symbols) {
        for (int i = 0; i < symbols.length(); i++) {
            String symbol = String.valueOf(symbols.charAt(i));
            
            if(!validSymbols.contains(symbol)) {
                throw new IllegalArgumentException("Symbol is not valid.");
            }
            
            currentState = currentState.getDestinationState(symbol);
        }
        
        return currentState.getValue();
    }
    
    private void initializeSymbols(String symbols) {
        String[] symbolTokens = symbols.split(",");
        validSymbols = new HashSet<String>(symbolTokens.length);       
        for (String symbolToken: symbolTokens) {
            validSymbols.add(symbolToken);
        }
    }
    
    private void initializeStates(String states) {
        String[] stateTokens = states.split(",");
        stateNameToState = new LinkedHashMap<String, State>(stateTokens.length);
        for (String stateToken : stateTokens) {
            String name = stateToken.split(":")[0];
            
            State state = new State(stateToken);
            
            if (currentState == null) {
                currentState = state;
            }
            
            stateNameToState.put(name, state);
        }
    }
    
    private void initializeTransitions(String transitions) {
        String[] transitionTokens = transitions.split(",");
        
        for (String transitionToken : transitionTokens) {
            String[] transitionMappings = transitionToken.split(":");
            
            String sourceName = transitionMappings[0];
            String destinationName = transitionMappings[1];
            String transitionSymbol = transitionMappings[2];
            
            if (!validSymbols.contains(transitionSymbol)) {
                throw new IllegalArgumentException("Symbol is not valid.");
            }
            
            State sourceState = stateNameToState.get(sourceName);
            State destinationState = stateNameToState.get(destinationName);
            sourceState.addTransition(transitionSymbol, destinationState);
        }
    }
        
}
