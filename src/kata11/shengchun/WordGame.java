package DTDojo.src.kata11.shengchun;

import java.util.*;
import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Dec 18, 2010
 * Time: 11:05:53 AM
 * To change this template use File | Settings | File Templates.
 */
public class WordGame {    
    List<String> dictionary= new ArrayList<String>();
    Map<String,Set<String>> relatedMap = new HashMap<String,Set<String>>();
    String _end;
    private Set<String> _endRelatedWords ;
    public WordGame(String fileName){
        inputDictionary(fileName);
    }
    
    public List<String> getRelated(String w){
        List<String> list = new ArrayList<String>();
        int length = w.length();
        for(int i=0;i < length;i++){
            String sub = i + (i==0?"":w.substring(0,i)) + (i == length -1 ?"":w.substring(i+1, length) );
            list.add(sub) ;
        }
        return list;
    }
    public void process(String start, String end){
        System.out.println(start + " " + end);
        List<String> startPatterns = getRelated(start);
        List<String> endPatterns =  getRelated(end);
        Set<String> startRelatedWords = getRelatedWords(startPatterns,start);
        Set<String> endRelatedWords = getRelatedWords(endPatterns,end);
        
        List<String> solutionWords = new ArrayList<String>();
        _end = end;
        _endRelatedWords = endRelatedWords;
        if(startRelatedWords.isEmpty() || endRelatedWords.isEmpty()){
            System.out.println("type 1");
        }
        else if(startRelatedWords.contains(end) || endRelatedWords.contains(start)){
            solutionWords.add(start);
            solutionWords.add(end);
            //System.out.println(start + " -> " + end);
        }
        else if(!Collections.disjoint(startRelatedWords, endRelatedWords)){
            String joinWord = findJoinWord(startRelatedWords);
            solutionWords.add(start);
            solutionWords.add(joinWord);
            solutionWords.add(end);
            /*
            if(joinWord != null){
               System.out.println(start + " -> " + joinWord + " -> " +end);
            }
            */
        }
        else {


            solutionWords.add(0, start);
            List<String> startList = new ArrayList<String>();
            startList.add(start);
            for(String choice: startRelatedWords){

                List<String> following = explore(choice, startList);
                if(following != null){
                    solutionWords.add(choice);
                    solutionWords.addAll(following);
                    solutionWords.add(_end);
                    break;
                }
            }
        }
        int i = 0;
        String solution = "";
        if(solutionWords.size() > 1 ){
           for(String s: solutionWords){
                 if(i++ > 0 ){
                    solution += "->" + s;
                 }
                 else {
                     solution = s;
                 }
           }
        }
        else  {
            solution = "unsolvable";
        }

        System.out.println(solution);
    }
    public List<String> explore(String startWord, List<String> startList){
        boolean find = false;
        List<String> following = new ArrayList<String>();
        List<String> startPatterns = getRelated(startWord);
        Set<String> startRelatedWords = getRelatedWords(startPatterns, startWord);

        if(startRelatedWords.isEmpty()){
            return null;
        }
        else if(startRelatedWords.contains(_end) || _endRelatedWords.contains(startWord)){
            following.add(startWord);
            find = true;
        }
        else if(!Collections.disjoint(startRelatedWords, _endRelatedWords)){            
            following.add(findJoinWord(startRelatedWords));
            find = true;
        }
        else {
            startList.add(startWord);

            for(String choice: startRelatedWords){
                List<String> following1 = new ArrayList<String>();
                //following1.add(choice);
                if(!startList.contains(choice)){
                    following1 = explore(choice, startList);
                    if(following1 != null){
                        following.add(choice);
                        following.addAll(following1);
                        find = true;
                        break;
                    }
                }
            }
        }
        if(find){
           return following;
        }
        else {
            return null;
        }

    }
    private String findJoinWord(Set<String> startSet){
        String joinWord = null;
             for(String word: startSet){
                if(_endRelatedWords.contains(word)){
                    joinWord = word;
                    break;
                }
        }
        return joinWord;
    }

    public Set<String> getRelatedWords(List<String> patternList, String word){
        Set<String>  relatedWords = new HashSet<String>();
        for(String p:patternList){
            if(relatedMap.containsKey(p)){
                relatedWords.addAll(relatedMap.get(p));
            }
        }
        relatedWords.remove(word);
        return relatedWords;
    }
    public void inputDictionary(String fileName){
        try {
            FileInputStream fis = new FileInputStream(new File(fileName));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = reader.readLine();
            List<String> related ;
            Set<String> relatedEntries;
            while(line != null ){
                dictionary.add(line.trim());
                related = getRelated(line);
                for(String s: related){
                    if(relatedMap.containsKey(s)){
                        relatedEntries = relatedMap.get(s);
                    }
                    else
                    {
                       relatedEntries = new HashSet<String>();
                    }
                    relatedEntries.add(line);
                    relatedMap.put(s,relatedEntries);

                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String argv[])
    {
        WordGame w1 = new WordGame(argv[0]);
        int size = w1.dictionary.size();

        long beginIndex = Math.round(Math.random()*size);
        long endIndex = Math.round(Math.random()*size);
        if(beginIndex == endIndex){
            beginIndex = endIndex ==0 ?endIndex + 1 : endIndex -1;

        }
        String start =  w1.dictionary.get((int)beginIndex);
        String end = w1.dictionary.get((int)endIndex);
        start= "book";
        end = "tree";
        //end = "bind";
        w1.process(start,end );
    }
}
