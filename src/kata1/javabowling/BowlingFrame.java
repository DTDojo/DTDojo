/**
 *
 *  Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of
 *  DemandTec, Inc. ("Confidential Information").
 *
 */

/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 3, 2010
 * Time: 9:15:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class BowlingFrame {
    private static final int MAX_PINS_IN_A_ROLL = 10;

    enum State {
        FIRST_BALL,
        SECOND_BALL,
        BONUS,
        DONE
    }

    private int total;
    private int bonusRolls;

    private State frameState;

    public BowlingFrame() {
        frameState = State.FIRST_BALL;
    }

    public void addPins(int pins) throws IllegalArgumentException, IllegalStateException {
        if (pins > MAX_PINS_IN_A_ROLL) {
            throw new IllegalArgumentException("Number of pins cannot exceed " + MAX_PINS_IN_A_ROLL);
        }
        State tempState = frameState;
        switch (tempState) {
            case BONUS:
                total += pins;
                bonusRolls--;
                if (bonusRolls == 0) {
                    frameState = State.DONE;
                }
                break;

            case FIRST_BALL:
                if (pins == MAX_PINS_IN_A_ROLL) {
                    //strike
                    chgToBonus(2);
                } else {
                    frameState = State.SECOND_BALL;
                }
                total += pins;
                break;
            case SECOND_BALL:
                int tempPins = (total + pins);
                if (tempPins > MAX_PINS_IN_A_ROLL) {
                    throw new IllegalArgumentException("Number of pins for 2 rolls in a frame cannot exceed " + MAX_PINS_IN_A_ROLL);
                } else  if (tempPins == MAX_PINS_IN_A_ROLL) {
                    //spare
                    chgToBonus(1);
                } else {
                    frameState = State.DONE;
                }
                total += pins;
                break;

            default:
                throw new IllegalStateException("This frame cannot accept any rolls!");

        }
    }

    public boolean isFrameDone() {
        return (frameState == State.DONE);
    }
    public boolean needsBonus() {
        return (frameState == State.BONUS);
    }
    public int getFrameScore() {
        return total;
    }
    /**
     * This method  changes the status of the frame to BONUS
     * and adds the number of bonus rolls
     * @param bonusCnt int number of expected bonus rolls
     */
    private void chgToBonus(int bonusCnt) {
        frameState = State.BONUS;
        bonusRolls = bonusCnt;
    }
}
