
/**
 * calculate bowling score
 * User: sye
 * Date: Nov 14, 2010
 * Time: 12:33:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class BowlScore {
    private String[] _scores;
    public BowlScore(String scoreList)
    {
         _scores = scoreList.replaceAll(" ","").split(",");
    }
    int score;
    public int getScores(){
    boolean firstPlay = true;
    int  currentGame = 0;
    Game[] games = new Game[12];
    int firstScore = 0;
    //Game g = new Game();
    for(String s: _scores){
        int currentScore = Integer.parseInt(s);
        if(games[currentGame] == null ){
           games[currentGame] = new Game();
        }
        if(firstPlay )
        {           
           games[currentGame].setFirstScore(currentScore);
            if(currentScore  == 10)
            {
                games[currentGame].setTotalScore(currentScore);
                games[currentGame].setIsStrike(true);
                currentGame++;
                firstScore = 0;
                firstPlay = true;
            }
            else
            {
                firstPlay = false;
                firstScore = currentScore;
            }
        }
        else
        {
            games[currentGame].setTotalScore(currentScore + firstScore);
                if((currentScore + firstScore) == 10 ){
                     games[currentGame].setIsSpare(true);
                }
            currentGame++;
            firstScore = 0;
            firstPlay = true;
        }
    }
        for(int i=0;i < 10;i++)
        {
            if(games[i].getIsStrike() ){
                score += games[i].getTotalScore() + games[i+1].getTotalScore();
                if (games[i+1].getIsStrike())
                {
                    if(i < 9 )
                        score +=  games[i+2].getTotalScore();
                    else {
                        //game 10 is strike and another two balls 10, #, add # to total
                        score +=  games[i+2].getFirstScore();
                    }
                }
                if (games[i+1].getIsSpare() && i < 9)
                {
                    //game 10 is strike followed by two balls , #, ## that is a spare but only count as # + ##
                    //Other games will count first ball of i+2
                    score +=  games[i+2].getFirstScore();
                }
            }
            else if(games[i].getIsSpare())
            {
                score += games[i].getTotalScore() + games[i+1].getFirstScore();
                if(games[i+1].getIsStrike()){
                    score += games[i+2].getFirstScore() ;
                }
            }
            else
            {
                score += games[i].getTotalScore();
            }
        }

    
        return score;
    }

    public static void main(String[] args)
    {
        BowlScore bs  = new BowlScore(args[0]);
        System.out.println("rolled: " + args[0] + " " + bs.getScores());
    }


}
