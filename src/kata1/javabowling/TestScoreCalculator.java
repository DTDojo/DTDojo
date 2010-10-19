/**
 *
 *  Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
 *
 *  This software is the confidential and proprietary information of
 *  DemandTec, Inc. ("Confidential Information").
 *
 */

import junit.framework.TestCase;

/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 3, 2010
 * Time: 7:11:56 PM
 * This class tests ScoreCalculator
 */
public class TestScoreCalculator extends TestCase {
    private ScoreCalculator player;


    protected void setUp() throws Exception {
        super.setUp();
        player = new ScoreCalculator("Test Player");
    }

    public void testOneRoll() {
        player.roll(5);
        assertEquals(5, player.getTotalScore());
        player.printTotalScore();
    }

    public void testIncorrectPinsForOneRoll() {
        try {
            player.roll(11);
            fail("Number of pins is greater than 10 in one roll, should get an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }

    public void testIncorrectPinsInaFrame() {
        player.roll(7);
        try {
            player.roll(7);
            fail("Total number of pins in a frame cannot exceed 10, should get an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
    public void testNoStrikesNoSpare() {
        player.roll(5);
        player.roll(4);
        player.roll(5);
        player.roll(3);

        assertEquals(17, player.getTotalScore());
        player.printTotalScore();

    }

    public void test2FramesOneStrike() {
        player.roll(10);
        player.roll(8);
        player.roll(1);
        assertEquals(28, player.getTotalScore());
    }

    public void test2FramesOneSpare() {
        player.roll(8);
        player.roll(2);
        player.roll(6);
        player.roll(3);
        assertEquals(25, player.getTotalScore());
    }

    public void testPerfectGame_10Strikes() {
        for (int i = 0; i < 12; i++) {
            player.roll(10);// 10 strikes + 2 bonuses of 10
        }
        assertEquals(300, player.getTotalScore());
    }

    public void test10Spares() {
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        //bonus
        player.roll(5);
        assertEquals(159, player.getTotalScore());
    }
    public void test22rolls() {
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        player.roll(6);
        player.roll(4);
        //bonus
        player.roll(5);
        //illegal roll
        try {
            player.roll(3);
            fail("Cannot exceed 21 rolls per game, expected IllegalStateException");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
    }

    public void testErraticGame() {
        player.roll(1);
        player.roll(4);

        player.roll(4);
        player.roll(5);

        player.roll(6);
        player.roll(4);

        player.roll(5);
        player.roll(5);

        player.roll(10);

        player.roll(0);
        player.roll(1);

        player.roll(7);
        player.roll(3);

        player.roll(6);
        player.roll(4);

        player.roll(10);
        
        player.roll(2);
        player.roll(8);
        player.roll(6);
        assertEquals(133, player.getTotalScore());
    }
}
