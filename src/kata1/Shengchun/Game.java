

/**
 * Score for each game
 * User: sye
 * Date: Nov 14, 2010
 * Time: 1:26:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    private int firstScore = 0;
    private int totalScore = 0;
    boolean isSpare = false;
    boolean isStrike = false;

    public void setFirstScore(int score)
    {
         firstScore = score;
    }
    public void setTotalScore(int score)
    {
         totalScore = score;
    }
    public void setIsStrike(boolean strike)
    {
         isStrike = strike;
    }
    public void setIsSpare(boolean strike)
    {
         isSpare = strike;
    }
    public int getFirstScore()
    {
         return firstScore;
    }
    public int getTotalScore()
    {
         return totalScore;
    }
    public boolean getIsStrike()
    {
         return isStrike;
    }
    public boolean getIsSpare()
    {
         return isSpare;
    }
}
