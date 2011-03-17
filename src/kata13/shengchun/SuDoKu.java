import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Mar 12, 2011
 * Time: 10:50:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class SuDoKu {
    private static final Integer DIMENSION = 9;
    private static final Integer SQUARE_DIMENSION = 3;
    private Map<Integer, List<Integer>> game ;
    Map<Integer, List<Integer>> totalColumns = new HashMap<Integer, List<Integer>>(DIMENSION);
    Map<Integer, List<Integer>> totalRows = new HashMap<Integer, List<Integer>>(DIMENSION) ;
    Map<Integer, List<Integer>> totalSquares = new HashMap<Integer, List<Integer>>(DIMENSION);
    public SuDoKu(){
        game =  new HashMap<Integer, List<Integer>>(DIMENSION*DIMENSION);

       for(int i=0;i<DIMENSION;i++){
           for(int j=0;j<DIMENSION;j++){
               List<Integer> node = new ArrayList<Integer>(DIMENSION);
               for(int n=1;n<=DIMENSION;n++){
                    node.add(n);
               }
               List<Integer> row = totalRows.get(i);
               if(row == null){
                   row = new ArrayList<Integer>();
               }
               row.add(i*DIMENSION+j);
               totalRows.put(i,row);

               List<Integer> column = totalColumns.get(j);
               if(column == null){
                   column = new ArrayList<Integer>();
               }
               column.add(i*DIMENSION+j);
               totalColumns.put(j,column);


               int squareIndex = (i/SQUARE_DIMENSION)*SQUARE_DIMENSION+j/SQUARE_DIMENSION;
               List<Integer> square = totalSquares.get(squareIndex);
               if(square == null){
                   square = new ArrayList<Integer>();
               }
               square.add(i*DIMENSION+j);
               totalSquares.put(squareIndex,square);

               game.put(i*DIMENSION+j,node);
           }
       }
    }

    public void parse(){
        boolean finished = onePassageParse();
        int passage = 1;
        while(!finished){
            passage++;
            finished = onePassageParse();
        }
        System.out.println( "solved in " + passage) ;
    }

    public boolean onePassageParse(){
        Map<Integer, List<Integer>> solvedColumns = new HashMap<Integer, List<Integer>>(DIMENSION);
        Map<Integer, List<Integer>> solvedRows = new HashMap<Integer, List<Integer>>(DIMENSION) ;
        Map<Integer, List<Integer>> solvedSquares = new HashMap<Integer, List<Integer>>(DIMENSION);

        int solved =0;
        for(int i=0; i<DIMENSION;i++){

            for(int j=0;j<DIMENSION;j++){
                int index = i*DIMENSION+j;
                int squareIndex = (i/SQUARE_DIMENSION)*SQUARE_DIMENSION+j/SQUARE_DIMENSION;

                List<Integer> l = game.get(index);
                if(l.size()== 1){
                    List<Integer> solvedRow = solvedRows.get(i);
                    if(solvedRow == null){
                       solvedRow = new ArrayList<Integer>();
                    }
                    solvedRow.addAll(l);
                    solvedRows.put(i,solvedRow);
                    List<Integer> solvedColumn = solvedColumns.get(j);
                    if(solvedColumn == null){
                       solvedColumn = new ArrayList<Integer>();
                    }
                    solvedColumn.addAll(l);
                    solvedColumns.put(j,solvedColumn);
                    List<Integer> solvedSquare = solvedSquares.get(squareIndex);
                    if(solvedSquare == null){
                       solvedSquare = new ArrayList<Integer>();
                    }
                    solvedSquare.addAll(l);
                    solvedSquares.put(squareIndex,solvedSquare);
                    solved++;
                }
            }
        }
        if(solved == DIMENSION*DIMENSION){
            return true;
        }
        for(int i=0;i < DIMENSION;i++){
            for(int j=0;j<DIMENSION;j++){
               int index = i*DIMENSION+j;
               /*
               row 0-2, col 0-2 square 1; col 3-5 square 2, col 6-8 square 3
               row 3-5, col 0-2 square 4; col 3-5 square 5, col 6-8 square 6
               row 6-8, col 0-2 square 7; col 3-5 square 8, col 6-8 square 9 
                */
               int squareIndex = (i/SQUARE_DIMENSION)*SQUARE_DIMENSION+j/SQUARE_DIMENSION;
               List<Integer> l = game.get(index);
                if(l.size() > 1){
                   Set<Integer> subtract = new HashSet<Integer>();
                    if(solvedRows.get(i) != null){
                        subtract.addAll(solvedRows.get(i));
                    }
                    if (solvedColumns.get(j) != null) {
                        subtract.addAll(solvedColumns.get(j));
                    }
                    if (solvedSquares.get(squareIndex) != null) {
                        subtract.addAll(solvedSquares.get(squareIndex));
                    }
                    for(Integer v:subtract){
                        l.remove(v);
                    }                    
                    game.put(index,l);
                }

            }
        }
        for(int i=0; i < DIMENSION;i++){
            List<Integer> rowMember = totalRows.get(i);
            parseSubGroup(rowMember);

            List<Integer> columnMember = totalColumns.get(i);
            parseSubGroup(columnMember);

            List<Integer> squareMember = totalSquares.get(i);
            parseSubGroup(squareMember);

            parseSubGroupForTriples(rowMember);
            parseSubGroupForTriples(columnMember);
            parseSubGroupForTriples(squareMember);

            parseSubGroupForQuadruples(rowMember);
            parseSubGroupForQuadruples(columnMember);
            parseSubGroupForQuadruples(squareMember);

            applyOnePossibilityRule(rowMember);
            applyOnePossibilityRule(columnMember);
            applyOnePossibilityRule(squareMember);
        }
        
        for(int i=0;i < DIMENSION;i++){
            for(int j=1;j < DIMENSION;j++){
                if(i < j){
                    parseRowXWing(i,j);
                    parseColumnXWing(i,j);
                }
            }
        }
        for(int i=0;i < DIMENSION;i++){
            for(int j=1;j < DIMENSION;j++){
                for(int k=2;k<DIMENSION;k++){
                if(i < j && j < k ){
                    parseRowSwordFish(i,j,k);
                    parseColumnSwordFish(i,j,k);
                }
                }
            }
        }

       
        return false;
    }
    public void parseSubGroup(List<Integer> groupMembers){
        List<Integer>  twoChoiceNode = new ArrayList<Integer> ();

        List<List<Integer>>  pairList = new ArrayList<List<Integer>> ();

        for(Integer index: groupMembers){
            List<Integer> node = game.get(index);
            if(node.size() == 2){
               twoChoiceNode.add(index);
            }
        }
        if(twoChoiceNode.size() == 2) {
            processPairToSubtract(groupMembers, twoChoiceNode);
        }
        else if(twoChoiceNode.size() > 2){
             for(int i=0;i < twoChoiceNode.size();i++){
                  for(int j=1;j < twoChoiceNode.size();j++){
                      if(i<j){
                          List<Integer> pair = new ArrayList<Integer>();
                          pair.add(twoChoiceNode.get(i));
                          pair.add(twoChoiceNode.get(j));
                          pairList.add(pair);
                      }
                  }
             }
            for(List<Integer> pair: pairList){
                processPairToSubtract(groupMembers, pair);
            }
        }
    }


public void parseSubGroupForTriples(List<Integer> groupMembers){
        List<Integer>  twoOrThreeChoiceNode = new ArrayList<Integer> ();

        List<List<Integer>>  tripleList = new ArrayList<List<Integer>> ();

        for(Integer index: groupMembers){
            List<Integer> node = game.get(index);
            if(node.size() == 2 || node.size() == 3){
               twoOrThreeChoiceNode.add(index);
            }
        }
        if(twoOrThreeChoiceNode.size() == 3) {
            processTripleToSubtract(groupMembers, twoOrThreeChoiceNode);
        }
        else if(twoOrThreeChoiceNode.size() > 3){
             for(int i=0;i < twoOrThreeChoiceNode.size();i++){
                  for(int j=1;j < twoOrThreeChoiceNode.size();j++){
                      for(int k = 2;k<twoOrThreeChoiceNode.size();k++){
                          if(i<j && j <k){
                              List<Integer> triple = new ArrayList<Integer>();
                              triple.add(twoOrThreeChoiceNode.get(i));
                              triple.add(twoOrThreeChoiceNode.get(j));
                              triple.add(twoOrThreeChoiceNode.get(k));
                              tripleList.add(triple);
                          }
                      }
                  }
             }
            for(List<Integer> triple: tripleList){
                processTripleToSubtract(groupMembers, triple);
            }
        }
    }

    public void parseSubGroupForQuadruples(List<Integer> groupMembers){
        List<Integer>  twoOrThreeOrFourChoiceNode = new ArrayList<Integer> ();

        List<List<Integer>>  quadrupleList = new ArrayList<List<Integer>> ();

        for(Integer index: groupMembers){
            List<Integer> node = game.get(index);
            if(node.size() == 2 || node.size() == 3 || node.size() == 4){
               twoOrThreeOrFourChoiceNode.add(index);
            }
        }
        if(twoOrThreeOrFourChoiceNode.size() == 4) {
            processSubgroupToSubtract(groupMembers, twoOrThreeOrFourChoiceNode);
        }
        else if(twoOrThreeOrFourChoiceNode.size() > 3){
             for(int i=0;i < twoOrThreeOrFourChoiceNode.size();i++){
                  for(int j=1;j < twoOrThreeOrFourChoiceNode.size();j++){
                      for(int k = 2;k<twoOrThreeOrFourChoiceNode.size();k++){
                          for(int l = 3;l<twoOrThreeOrFourChoiceNode.size();l++){
                          if(i<j && j <k && k < l){
                              List<Integer> quadruple = new ArrayList<Integer>();
                              quadruple.add(twoOrThreeOrFourChoiceNode.get(i));
                              quadruple.add(twoOrThreeOrFourChoiceNode.get(j));
                              quadruple.add(twoOrThreeOrFourChoiceNode.get(k));
                              quadruple.add(twoOrThreeOrFourChoiceNode.get(l));
                              quadrupleList.add(quadruple);
                          }
                      }

                      }
                  }
             }
            for(List<Integer> triple: quadrupleList){
                processTripleToSubtract(groupMembers, triple);
            }
        }
    }

    public void applyOnePossibilityRule(List<Integer> groupMembers){
        Map<Integer,List<Integer>>  possibilityMap = new HashMap<Integer,List<Integer>> ();        

        for(Integer index: groupMembers){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap.put(value,usage);
            }
        }
        for(Integer value: possibilityMap.keySet()){
            if(possibilityMap.get(value).size() == 1){
                List<Integer> usage = possibilityMap.get(value);
                int index = usage.get(0);
                List<Integer>  node = new ArrayList<Integer>();
                node.add(value);
                game.put(index,node);
            }
        }
    }

    public void processPairToSubtract(List<Integer> groupMembers, List<Integer> pair){
        List<Integer>  subtractList = new ArrayList<Integer> ();
        List<Integer> member1 = game.get(pair.get(0));
        List<Integer> member2 = game.get(pair.get(1));
        Collections.sort(member1);
        Collections.sort(member2);
        if(member1.equals(member2)){
           subtractList.addAll(member1);
        }
        if(subtractList.size() >0 ){
           for(Integer index: groupMembers){
                List<Integer> node = game.get(index);
                if(!pair.contains(index) && node.size() > 1){
                   node.removeAll(subtractList);
                    game.put(index,node);
                }
           }
        }
    }

    public void processTripleToSubtract(List<Integer> groupMembers, List<Integer> triple){
        List<Integer>  subtractList = new ArrayList<Integer> ();
        List<Integer> member1 = game.get(triple.get(0));
        List<Integer> member2 = game.get(triple.get(1));
        List<Integer> member3 = game.get(triple.get(2));

        Set<Integer> s = new HashSet<Integer>();
        s.addAll(member1);
        s.addAll(member2);
        s.addAll(member3);
        if(s.size() == 3 ){
           subtractList.addAll(s);
        }

        if(subtractList.size() >0 ){
           for(Integer index: groupMembers){
                List<Integer> node = game.get(index);
                if(!triple.contains(index) && node.size() > 1){
                    node.removeAll(subtractList);
                    game.put(index,node);
                }
           }
        }
    }

    public void processSubgroupToSubtract(List<Integer> groupMembers, List<Integer> subGroup){
        List<Integer>  subtractList = new ArrayList<Integer> ();
        int groupSize = subGroup.size();

        Set<Integer> s = new HashSet<Integer>();
        for(Integer index:subGroup){
            s.addAll(game.get(index));
        }
        if(s.size() == groupSize ){
           subtractList.addAll(s);
        }

        if(subtractList.size() >0 ){
           for(Integer index: groupMembers){
                List<Integer> node = game.get(index);
                if(!subGroup.contains(index) && node.size() > 1){
                    node.removeAll(subtractList);
                    game.put(index,node);
                }
           }
        }
    }
    public void parseRowXWing(int row1, int row2){
        Map<Integer,List<Integer>>  possibilityMap1 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap2 = new HashMap<Integer,List<Integer>> ();

        for(Integer index: totalRows.get(row1)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap1.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap1.put(value,usage);
            }
        }
        for(Integer index: totalRows.get(row2)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap2.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap2.put(value,usage);
            }
        }
        for(Integer value: possibilityMap1.keySet()){
            if(possibilityMap1.get(value).size() == 2 && possibilityMap2.get(value).size() == 2 ){
                List<Integer> usage1 = possibilityMap1.get(value);
                List<Integer> usage2 = possibilityMap2.get(value);
                Set<Integer>  columns = new HashSet<Integer>();
                for(int index1: usage1){
                    int column = index1%DIMENSION;
                    columns.add(column);
                }
                for(int index1: usage2){
                    int column = index1%DIMENSION;
                    columns.add(column);
                }
                if(columns.size() == 2) {
                    for(int wingColumn: columns){
                        List<Integer> columnMembers = totalColumns.get(wingColumn);
                        for(int index: columnMembers){
                            int row = index/DIMENSION;
                            if (row != row1 && row != row2){
                                List<Integer> node = game.get(index);
                                if(node.size() > 1 ){
                                node.remove(value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void parseColumnXWing(int column1, int column2){
        Map<Integer,List<Integer>>  possibilityMap1 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap2 = new HashMap<Integer,List<Integer>> ();

        for(Integer index: totalColumns.get(column1)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap1.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap1.put(value,usage);
            }
        }
        for(Integer index: totalColumns.get(column2)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap2.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap2.put(value,usage);
            }
        }
        for(Integer value: possibilityMap1.keySet()){
            //System.out.println("columns" +column1 + column2 + " value " +value);
            if(possibilityMap1.get(value).size() == 2 && possibilityMap2.get(value).size() == 2 ){
                List<Integer> usage1 = possibilityMap1.get(value);
                List<Integer> usage2 = possibilityMap2.get(value);
                Set<Integer>  rows = new HashSet<Integer>();
                for(int index1: usage1){
                    int row = index1/DIMENSION;
                    rows.add(row);
                }
                for(int index1: usage2){
                    int row = index1/DIMENSION;
                    rows.add(row);
                }
                if(rows.size() == 2) {
                    for(int wingRow: rows){
                        List<Integer> rowMembers = totalRows.get(wingRow);
                        for(int index: rowMembers){
                            int column = index%DIMENSION;
                            if (column != column1 && column != column2){
                                List<Integer> node = game.get(index);
                                if(node.size() > 1 ){
                                    node.remove(value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void parseRowSwordFish(int row1, int row2, int row3){
        Map<Integer,List<Integer>>  possibilityMap1 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap2 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap3 = new HashMap<Integer,List<Integer>> ();

        for(Integer index: totalRows.get(row1)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap1.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap1.put(value,usage);
            }
        }
        for(Integer index: totalRows.get(row2)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap2.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap2.put(value,usage);
            }
        }
        for(Integer index: totalRows.get(row3)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap3.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap3.put(value,usage);
            }
        }

        for(Integer value: possibilityMap1.keySet()){
            if((possibilityMap1.get(value).size() == 2 || possibilityMap1.get(value).size() == 3 )
                    && (possibilityMap2.get(value).size() == 2 || possibilityMap2.get(value).size() == 3 )
                    && (possibilityMap2.get(value).size() == 2 || possibilityMap2.get(value).size() == 3 )
            ){
                List<Integer> usage1 = possibilityMap1.get(value);
                List<Integer> usage2 = possibilityMap2.get(value);
                List<Integer> usage3 = possibilityMap3.get(value);
                Set<Integer>  columns = new HashSet<Integer>();
                for(int index1: usage1){
                    int column = index1%DIMENSION;
                    columns.add(column);
                }
                for(int index1: usage2){
                    int column = index1%DIMENSION;
                    columns.add(column);
                }
                for(int index1: usage3){
                    int column = index1%DIMENSION;
                    columns.add(column);
                }
                if(columns.size() == 3) {
                    for(int wingColumn: columns){
                        List<Integer> columnMembers = totalColumns.get(wingColumn);
                        for(int index: columnMembers){
                            int row = index/DIMENSION;
                            if (row != row1 && row != row2 && row != row3){
                                List<Integer> node = game.get(index);
                                if(node.size() > 1 ){
                                    node.remove(value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void parseColumnSwordFish(int column1, int column2, int column3){
        Map<Integer,List<Integer>>  possibilityMap1 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap2 = new HashMap<Integer,List<Integer>> ();
        Map<Integer,List<Integer>>  possibilityMap3 = new HashMap<Integer,List<Integer>> ();

        for(Integer index: totalColumns.get(column1)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap1.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap1.put(value,usage);
            }
        }
        for(Integer index: totalColumns.get(column2)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap2.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap2.put(value,usage);
            }
        }
        for(Integer index: totalColumns.get(column3)){
            List<Integer> node = game.get(index);
            for(Integer value: node){
                List<Integer> usage = possibilityMap3.get(value);
                if(usage == null){
                    usage = new ArrayList<Integer>();
                }
                usage.add(index);
                possibilityMap3.put(value,usage);
            }
        }

        for(Integer value: possibilityMap1.keySet()){
            //System.out.println("columns" +column1 + column2 + " value " +value);
            if(        (possibilityMap1.get(value).size() == 2 || possibilityMap1.get(value).size() == 3)
                    && (possibilityMap2.get(value).size() == 2 || possibilityMap2.get(value).size() == 3)
                    && (possibilityMap3.get(value).size() == 2 || possibilityMap3.get(value).size() == 3)
            ){
                List<Integer> usage1 = possibilityMap1.get(value);
                List<Integer> usage2 = possibilityMap2.get(value);
                List<Integer> usage3 = possibilityMap3.get(value);
                Set<Integer>  rows = new HashSet<Integer>();
                for(int index1: usage1){
                    int row = index1/DIMENSION;
                    rows.add(row);
                }
                for(int index1: usage2){
                    int row = index1/DIMENSION;
                    rows.add(row);
                }
                for(int index1: usage3){
                    int row = index1/DIMENSION;
                    rows.add(row);
                }
                if(rows.size() == 3) {
                    for(int swordRow: rows){
                        List<Integer> rowMembers = totalRows.get(swordRow);
                        for(int index: rowMembers){
                            int column = index%DIMENSION;
                            if (column != column1 && column != column2 && column != column3){
                                List<Integer> node = game.get(index);
                                if(node.size() > 1 ){
                                    node.remove(value);
                                }
                            }
                        }
                    }
                }
            }
        }
    }


    public void parseInput(String input){
        char[] a = input.toCharArray();
        int index = 0;
        for(int k=0; k < a.length;k++){
             if(a[k] == ' '){                 
             }
             else {
                 char b = a[k];
                 if(b == 'x'){
                    
                 }
                 else {
                     List<Integer> l = new ArrayList<Integer>();
                     l.add(new Integer("" + b));
                     this.game.put(index,l) ;
                 }
                 index++;
             }
        }
    }
    public Map<Integer,List<Integer>> getGame(){
        return game;
    }
    
    public static void main(String[] args){
        String input = args[0];        
        /*
        //input = "586xxxx12 xxxx5286x 24x81xxx3 xxx5x3x9x xxxx8124x 4x56xx738 x5x23xx81 7xxxx8xxx 36xxx5xxx";
        input = "xxxx84xx5 xx6x3xxx7 x795xxxxx x5xxx17x6 x8xxxxx9x 9x76xxx3x xxxxx967x 4xxx5x2xx 7xx26xxxx";  //hard
        //input = "9xxxxxx42 xxxxx7x18 xxx32xxxx x3x4xx7xx xx7x3x9xx xx5xx2x3x xxxx91xxx 74x8xxxxx 82xxxxxx6"; //evil
        input = "xxxxx9x83 78xx2xxx6 xxx58xxxx x53xxxxxx 8xxxxxxx1 xxxxxx62x xxxx15xxx 8xxx3xx52 24x9xxxxx";   //evil  locked
        input = "x14859xxx xx9xxxx2x x6x2x4xxx 6x2xxxx4x xxxxxxxxx x9xxxx5x7 xxx9x7x6x x7xxxx8xx xxx56347x";   //evil Evil Puzzle 378,471,639 locked
        input = "xx1x59x4x xxxxxxxx5 xxx2xx67x 1x2xxxxxx 5xx8x1xx7 xxxxxx9x6 x26xx5xxx 7xxxxxxxx x9x74x8xx";   //evil Evil Puzzle 196,059,185
        input = "xx7x1xxxx x93xxxxxx 4xxx36xx7 x4xxx79xx 2xxxxxxx1 xx64xxx3x 7xx35xxx6 xxxxxx12x xxxx8x4xx";  //Evil Puzzle 6,890,920,588
        //input = "xxx37xxxx 8x5xxxx6x x7x8xxx9x xxx5x1x7x xx4x3x9xx x3x6x4xxx x1xxx8x4x x9xxxx2x5 xxxx65xxx"; // Evil Puzzle 8,316,490,404  locked
        */
        SuDoKu suDoKu = new SuDoKu();
        suDoKu.parseInput(input);
        suDoKu.parse();
        for(int n=0;n< DIMENSION*DIMENSION;n++){
            System.out.print(suDoKu.game.get(n));
            if(n%9 == 8){
                System.out.print("\n");
            }           
        }

    }
}
