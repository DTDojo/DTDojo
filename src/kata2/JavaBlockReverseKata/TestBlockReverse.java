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

import java.util.Arrays;

import junit.framework.TestCase;

public class TestBlockReverse extends TestCase {

        public void testNoNegative() {
            Integer[] toReverse = {2, 3, 4, 5, 6, 7};
            Integer[] expected = {2, 3, 4, 5, 6, 7};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testAllNegative() {
            Integer[] toReverse = {-1, -2, -3, -4, -5};
            Integer[] expected = {-5, -4, -3, -2, -1};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testStartsAndEndsWithPositive() {
            Integer[] toReverse = {2, 3, 4, -1, 7, 8, -3, 5, 6, 7};
            Integer[] expected = {5, 6, 7, -3, 7, 8, -1, 2, 3, 4};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testStartsAndEndsWithNegative() {
            Integer[] toReverse = {-6, 2, 3, 4, -1, 7, 8, -3, 5, 6, 7, -20};
            Integer[] expected = {-20, 5, 6, 7, -3, 7, 8, -1, 2, 3, 4, -6};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testStartsPositiveEndsNegative() {
            Integer[] toReverse = {2, 3, 4, -1, 7, 8, -3, 5, 6, 7, -20};
            Integer[] expected = {-20, 5, 6, 7, -3, 7, 8, -1, 2, 3, 4};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testStartsNegativeEndsPositive() {
            Integer[] toReverse = {-7, 2, 3, 4, -1, 7, 8, -3, 5, 6, 7};
            Integer[] expected = {5, 6, 7, -3, 7, 8, -1, 2, 3, 4, -7};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testEmpty() {
            Integer[] toReverse = {};
            Integer[] expected = {};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        public void testOne() {
            Integer[] toReverse = {-1};
            Integer[] expected = {-1};
            assertArrayEquals(expected, BlockReverse.block_reverse(toReverse));
        }
        
        private void assertArrayEquals(Integer[] expected, Integer[] actual) 
        {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual: " + Arrays.toString(actual));
            if (expected.length != actual.length) {
                fail("Size not equal; arrays are not the same");
            }
            
            for (int i = 0; i < expected.length; i++) {
                assertEquals(expected[i], actual[i]);
            }
        }
}
