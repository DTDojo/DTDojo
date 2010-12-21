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

public class Bowler {
    private static final Integer MAX_FRAMES = 10;
    
    private List<Frame> frames = new ArrayList<Frame>();
    private Frame currentFrame;
    
    public Bowler() {
        currentFrame = new NormalFrame();
        frames.add(currentFrame);
    }
    
    public int getTotal() {
        int total = 0;
        
        for (int frameNum = 0; frameNum < Math.min(frames.size(), MAX_FRAMES); frameNum++) {            
            Frame frame = frames.get(frameNum);
            total += frame.getScore();
            
            if (frame.isStrike() || frame.isSpare()) {
                total = addFirstBonusRoll(total, frameNum);
            }
            
            if (frame.isStrike()) {
                total = addSecondBonusRoll(total, frameNum);
            }
        }
        
        return total;
    }

    public void roll(int pinsKnockedOver) {
        if (currentFrame.isComplete()) {
            if (frames.size() >= MAX_FRAMES) {
                currentFrame = new BonusFrame();
            } else {
                currentFrame = new NormalFrame();
            }
            frames.add(currentFrame);
        } 
        
        currentFrame.addRoll(pinsKnockedOver);
    }
    
    private int addFirstBonusRoll(int total, int frameNum) {
        Frame nextFrame = getNextFrame(frameNum);
        
        if (nextFrame != null) {
            Integer nextRoll = nextFrame.getFirstRoll();
            if (nextRoll != null) {
                total += nextRoll;
            }
        }
        return total;
    }
    
    private int addSecondBonusRoll(int total, int frameNum) {
        Frame nextFrame = getNextFrame(frameNum);
        
        if (nextFrame != null) {
            if (nextFrame.getSecondRoll() != null) {
                total += nextFrame.getSecondRoll();
            } else {
                //Next frame is also a strike; get the next next frame
                nextFrame = getNextFrame(frameNum + 1);
                if (nextFrame != null && nextFrame.getFirstRoll() != null) {
                    total += nextFrame.getFirstRoll();
                }
            }
        }
        return total;
    }
    
    private Frame getNextFrame(int frameNum) {
        if (frameNum + 1 >= frames.size()) {
            return null;
        }
        
        return frames.get(frameNum + 1);
    }
}
