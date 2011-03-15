

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Feb 26, 2011
 * Time: 4:56:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class GroupSum {
    public List<List<Integer>> getListFromRawList(List<Integer> sourceList, Integer sum){        
        Collections.sort(sourceList);
        return getListFromSortedList(sourceList, sum);
    }
    public List<List<Integer>> getListFromSortedList(List<Integer> sourceList, Integer sum){
        int total =0;
        //System.out.println("source list " +sourceList) ;
        //System.out.println("sum " +sum) ;
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        for(int i = 0; i < sourceList.size();i++){
            Integer a = sourceList.get(i);
            //throw any number greater than sum and after away
            if(a > sum && a > 0 ){
               sourceList = sourceList.subList(0,i);
            }
            else {
                total += a;
            }
        }
        //if total of remaining number less than sum, we will not be able to get a solution
        if (total < sum || sourceList.size() == 0 ){
            return results;
        }
        if(total == sum){  //all numbers add together to be a solution return that solution and stop
            List<Integer> l = new ArrayList<Integer>();
            for(int i:sourceList){
               l.add(i);
            }            
            results.add(l);
            return results;
        }
        int lastNumber = sourceList.get(sourceList.size()-1);
        if(lastNumber == sum){   //last number is an solution
            
            List<Integer> l = new ArrayList<Integer>();
            l.add(lastNumber);
            results.add(l);
        }
        else if(lastNumber > sum  || (lastNumber + sourceList.get(0)) > sum){   //last number greater than sum or last number + first number > sum, we will not be able to get a soultion with last number, ignore last number

        }
        else if(sourceList.size() > 1){

            //we might have a potential solution with last number, try to find a solution that make up the difference of sum and lastNumber
           List<Integer> source1 = sourceList.subList(0,sourceList.size()-1);

           List<List<Integer>> results0 =  getListFromSortedList(source1, sum-lastNumber);
            for(List<Integer> l: results0){
                l.add(lastNumber);
                results.add(l);
            }           
        }
        if(sourceList.size() > 1){
            List<Integer> source1 = sourceList.subList(0,sourceList.size()-1);
            List<List<Integer>> results1 = getListFromSortedList(source1, sum);
            if(results1.size() > 0){
                results.addAll(results1);
            }
        }

        return results;
    }
    public static void main(String[] argvs){
        String members = argvs[0];
        String sum = argvs[1];
        System.out.println("sum " + sum) ;
        String[] list = members.split(",");
        List<Integer> sourceList = new ArrayList<Integer>();
        for(String s: list){
            sourceList.add(new Integer(s));
        }
        GroupSum gs = new GroupSum();
        System.out.println("source list " +sourceList) ;
        List<List<Integer>> results = gs.getListFromRawList(sourceList,new Integer(sum));
        for(List<Integer> l : results){
            System.out.println(l);            
        }

    }
}
