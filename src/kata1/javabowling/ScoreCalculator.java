import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sgites
 * Date: Oct 3, 2010
 * Time: 7:11:04 PM
 * This Class calculates bowling scores for a player in a game
 */
public class ScoreCalculator {
    private static final int MAX_ALLOWABLE_ROLLS = 21;
    private static final int MAX_FRAME_NUM = 10;
    private String playerName;
    private int totalScore;
    private int totalRolls;
    private List<BowlingFrame> frames;
    private List<Integer> unfinishedFrameIdx;

    /**
     * Constructor.
     * Constructs the ScoreCalculator for a Player
     * @param playerName String name of the player
     */
    public ScoreCalculator(String playerName) {
        this.playerName = playerName;
        frames = new ArrayList<BowlingFrame>(MAX_FRAME_NUM);
        unfinishedFrameIdx = new ArrayList<Integer>();
        totalRolls = 0;
    }

    /**
     * This method returns total score for a game
     * @return  int score 
     */
    public int getTotalScore() {
        return totalScore + getScoreForUnfinishedFrames();
    }

    /**
     * This method returns player's name
     * @return String player's name
     */
    public String getPlayerName() {
        return playerName;
    }



    /**
     * This method adds pins that player rolls
     * @param pins int
     * @throws IllegalArgumentException if number of pins is greater than 10
     * @throws IllegalStateException if the number of total pins in a frame is greater than 10, or total number of rolls is greater than 21
     */
    public void roll(int pins) throws IllegalArgumentException, IllegalStateException {
        totalRolls++;
        if (totalRolls > MAX_ALLOWABLE_ROLLS) {
            throw new IllegalStateException("This game cannot have any more rolls!");
        }
        BowlingFrame currFrame;
        int index = 0;
        if (frames.isEmpty()) {
            currFrame = new BowlingFrame();
            frames.add(currFrame);
        }  else {
            // get the last frame
            index = frames.size() - 1;
            currFrame = frames.get(index);
        }
        if (!currFrame.isFrameDone()) {
            currFrame.addPins(pins);
        }
        int start = 1;
        if (currFrame.isFrameDone() || currFrame.needsBonus()) {
            if (frames.size() < MAX_FRAME_NUM) {
                frames.add(new BowlingFrame());
            }

            if (currFrame.isFrameDone()) {
                totalScore += currFrame.getFrameScore();
            } else {
                //bonus
                if (unfinishedFrameIdx.isEmpty()) {
                    unfinishedFrameIdx.add(index);
                } else {
                    if (index != unfinishedFrameIdx.get(unfinishedFrameIdx.size() - 1)) {
                        unfinishedFrameIdx.add(index);
                    }
                }
                start = 2;
            }
        }
        // add the bonus rolls
        addBonusRolls(start, pins);

    }


    /**
     * This method prints total score for a game
     */
    public void printTotalScore() {
        System.out.println("Total score for " + getPlayerName() + ": " + getTotalScore());
    }

    //-----------------------------------------------
    // Private methods
    //-----------------------------------------------
    private int getScoreForUnfinishedFrames() {
           int temp = 0;
           if (!frames.isEmpty()) {
               if (frames.size() < MAX_FRAME_NUM) {
                   for (int i = frames.size() - 1; i >= 0; i--) {
                       temp = getScoreForOneUnfinishedFrame(i, temp);
                   }
               } else {
                   for (int i = unfinishedFrameIdx.size() - 1; i >= 0; i--) {
                       temp = getScoreForOneUnfinishedFrame(unfinishedFrameIdx.get(i), temp);
                   }
               }
           }
           return temp;
       }

    private int getScoreForOneUnfinishedFrame(int frameIdx, int temp) {
        int retValue = temp;
        BowlingFrame tempFr = frames.get(frameIdx);
        if (!tempFr.isFrameDone()) {
            retValue = temp + tempFr.getFrameScore();
        }
        return retValue;
    }
    // add bonus rolls to the unifinished frames awaiting bonuses.
    private void addBonusRolls(int start, int pins) {
        for (int i = unfinishedFrameIdx.size() - start; i >= 0; i--) {
            BowlingFrame tempFrame = frames.get(unfinishedFrameIdx.get(i));
            if (tempFrame.needsBonus()) {
                tempFrame.addPins(pins);
                if (tempFrame.isFrameDone()) {
                    unfinishedFrameIdx.remove(i);
                    totalScore += tempFrame.getFrameScore();
                }
            }
        }
    }


}
