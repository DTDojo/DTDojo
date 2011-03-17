import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Mar 14, 2011
 * Time: 11:36:25 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSuDoKu {
    @Test
    public void test1(){
        //Coding Dojo example
        String input = "586xxxx12 xxxx5286x 24x81xxx3 xxx5x3x9x xxxx8124x 4x56xx738 x5x23xx81 7xxxx8xxx 36xxx5xxx";
        confirmSuDoKu(input);
    }

    @Test
    public void test2(){
        //Web SuDoKu Hard
        String input = "xxxx84xx5 xx6x3xxx7 x795xxxxx x5xxx17x6 x8xxxxx9x 9x76xxx3x xxxxx967x 4xxx5x2xx 7xx26xxxx";
        confirmSuDoKu(input);
    }

    @Test
    public void test3(){
        //Web SuDoKu Evil
        String input = "9xxxxxx42 xxxxx7x18 xxx32xxxx x3x4xx7xx xx7x3x9xx xx5xx2x3x xxxx91xxx 74x8xxxxx 82xxxxxx6";
        confirmSuDoKu(input);
    }

    @Test
    public void test4(){
        //Web SuDoKu Evil Puzzle 196,059,185
        String input = "xx1x59x4x xxxxxxxx5 xxx2xx67x 1x2xxxxxx 5xx8x1xx7 xxxxxx9x6 x26xx5xxx 7xxxxxxxx x9x74x8xx";
        confirmSuDoKu(input);
    }

    @Test
    public void test5(){
        //Web SuDoKu Evil Puzzle 6,890,920,588
        String input = "xx7x1xxxx x93xxxxxx 4xxx36xx7 x4xxx79xx 2xxxxxxx1 xx64xxx3x 7xx35xxx6 xxxxxx12x xxxx8x4xx";
        confirmSuDoKu(input);
    }

    @Test
    public void test6(){
        //Web SuDoKu Evil Puzzle 2,425,773,864
        String input = "x3xxxx85x x9x1xxxxx xx58xx4xx 7x863xxxx xxxx1xxxx xxxx485x6 xx1xx73xx xxxxx9x4x x26xxxx7x";
        confirmSuDoKu(input);
    }

    @Test
    public void test7(){        
        String input = "x1xx3xxx7 xxxxx93xx xx357xx8x xxxxxx6x2 5xx1x3xx9 6x4xxxxxx x6xx978xx xx76xxxxx 2xxx8xx9x";
        confirmSuDoKu(input);
    }

    @Test
    public void test8(){
        //WEb SuDoKu Evil Puzzle 4,967,250,021
        String input = "9xx6xxxx5 xx74xx9xx 6xxx7xxxx xx1xxxxx6 3x6x1x7x2 4xxxxx3xx xxxx5xxx1 xx3xx64xx 2xxxx8xx3";
        confirmSuDoKu(input);
    }

    @Test
    public void test9(){
        ///Evil Puzzle 4,365,977,930
        String input = "7xxxxx5xx 3x51xxxxx xxx9x7x3x x4xx2x6xx x5x7x8x4x xx1x3xx8x x2x6x9xxx xxxxx59x7 xx4xxxxx6";
        confirmSuDoKu(input);
    }

    @Test
    public void test10(){
        //Web SuDoKu Evil Puzzle 3,533,480,979
        String input = "4xx5x1xxx xx9xxxxx1 x52xxxxx4 x4x76xxxx 6xxx1xxx8 xxxx85x4x 9xxxxx83x 2xxxxx6xx xxx6x7xx5";
        confirmSuDoKu(input);
    }

    @Test
    public void test11(){
        //Web SuDoKu Evil Puzzle 378,471,639
        //String input = "x14859xxx xx9xxxx2x x6x2x4xxx 6x2xxxx4x xxxxxxxxx x9xxxx5x7 xxx9x7x6x x7xxxx8xx xxx56347x";
        String input = "x14859xx6 xx9xxxx24 x6x2x4xx8 6x2xxxx49 xxxxxxxx2 x9xxxx5x7 xxx9x7x6x x7xxxx8xx xxx563471";
        confirmSuDoKu(input);
    }

    public void confirmSuDoKu(String input){
        SuDoKu suDoKu = new SuDoKu();
        suDoKu.parseInput(input);
        suDoKu.parse();
        Map<Integer, Integer> columnSums = new HashMap<Integer, Integer>(9);
        Map<Integer, Integer> rowSums = new HashMap<Integer, Integer>(9);
        Map<Integer, Integer> squareSums = new HashMap<Integer, Integer>(9);
        Map<Integer, List<Integer>> game = suDoKu.getGame();
        Set<Integer> indices = game.keySet();

        for(int i=0; i < 81;i++){
            int index = i;
            int row = index/9;
            int column = index%9;
            int square = ((index/9)/3)*3+(index%9)/3;
            int rowSum ;
            int value = game.get(index).get(0);
            System.out.print(game.get(index));
            if(column == 8){
                System.out.print("\n");
            }
            if(!rowSums.containsKey(row)){
                 rowSum = value;
            }
            else {
                rowSum = rowSums.get(row) + value;
            }

            rowSums.put(row, rowSum);

            int columnSum ;
            if(!columnSums.containsKey(column)){
                 columnSum = value;
            }
            else {
                columnSum = columnSums.get(column) + value;
            }
            columnSums.put(column,columnSum);

            int squareSum ;
            if(!squareSums.containsKey(square)){
                 squareSum = value;
            }
            else {
                squareSum = squareSums.get(square) + value;
            }
            squareSums.put(square,squareSum);
        }
        for(int i=0;i<9;i++){
            assertEquals(45,(long)rowSums.get(i));
            assertEquals(45,(long)columnSums.get(i));
            assertEquals(45,(long)squareSums.get(i));
        }
    }
}
