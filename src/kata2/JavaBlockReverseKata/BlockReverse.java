/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Oct 11, 2010
*
* @author Andrew Nguyen
*/ 

package com.demandtec.codingdojo.BlockReverseKata;

import java.util.ArrayList;
import java.util.List;

public class BlockReverse {  
    
    public static Integer[] block_reverse(Integer[] toReverse) {
        int size = toReverse.length;
        List<Integer> reversedResult = new ArrayList<Integer>(size);
        List<Integer> block = new ArrayList<Integer>();
                
        for (int i = size - 1; i >= 0; i--) {            
            if (toReverse[i] < 0) {
                if (!block.isEmpty()) {
                    reversedResult.addAll(block);
                    block.clear();
                }
                reversedResult.add(toReverse[i]);
            } else {
                block.add(0, toReverse[i]);
            }
        }
               
        reversedResult.addAll(block);
        
        return reversedResult.toArray(new Integer[size]);
    }
}