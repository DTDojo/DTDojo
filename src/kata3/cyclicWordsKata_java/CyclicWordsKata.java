/**
* Copyright (c) 2010 DemandTec, Inc. All Rights Reserved.
*
* This software is the confidential and proprietary information of
* DemandTec, Inc. ("Confidential Information").
*
* Created on Oct 17, 2010
*
* @author [SYE]
*/
package cyclicWordsKata;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
@Author: Sye
 */
public class CyclicWordsKata {
    String[] _input;
    public CyclicWordsKata(String[] input)
    {
         _input = input;
    }
    public List<List<String>> process()
    {
        List<List<String>> processed = new ArrayList<List<String>>();
        Map<Integer,List<String>> buckets ;
        List<List<String>> returnArray ;
        buckets = separateInputBySize(_input);
        if(buckets == null)
        return processed;
        for(int size: buckets.keySet())
        {
            List<String> elements = buckets.get(size);
            System.out.println("size " + size);
            while(elements != null && elements.size() > 0) 
            {
                returnArray = processSameSizeArray(elements);
                elements  = returnArray.get(1) ;

                processed.add(returnArray.get(0));

            }
        }
        return processed;
    }



    public Map<Integer,List<String>> separateInputBySize(String[] input)
    {
        Map<Integer,List<String>> buckets = new HashMap<Integer,List<String>>();
        if(input == null)
        {
            return buckets;
        }
        for(int i = 0;i < input.length;i++)
        {
            int stringLength = input[i].length();
            if(stringLength == 0){
                continue;
            }
            List<String> subList = buckets.get(stringLength);

            if(subList == null  )
            {
                subList = new ArrayList<String>() ;
                subList.add(input[i]);                
            }
            else
            {
                subList.add( input[i]);
            }
            buckets.put(stringLength,subList);
        }
        return buckets;
    }
    private List<List<String>> processSameSizeArray(List<String> input) {
        List<String> unmatched = new ArrayList<String>();
        List<String> matched = new ArrayList<String>();
        List<List<String>> list = new ArrayList<List<String>>();
        if(input == null){
           return list;
        }
        if(input.size() == 1 ){
            matched = input;
            unmatched = null;
        }
        else
        {
            String firstString = input.get(0);
            String fullString = firstString + firstString;
            matched.add(firstString);
            for(int i=1;i < input.size();i++)
            {
                if(input.get(i) == null || input.get(i).length() == 0 ) continue;
                int index =  fullString.indexOf(input.get(i));
                if(index < 0 ){
                     unmatched.add(input.get(i));
                }
                else if(index == 0 )
                {
                     //same string appears again, skip
                }
                else if(index > 0 )
                {
                     matched.add(input.get(i));
                }
            }
        }
        list.add(matched);
        list.add(unmatched);
        return list;
    }
    public String joinStringArray(List<String> arrayList, String connector)
    {
        StringBuffer s = new StringBuffer();
         if(arrayList.size() == 1){
             return arrayList.get(0);
         }
         else
         {
            for (String str: arrayList) {
                if(str != null)
                {
                    if(s.length() >0  )
                    {
                        s.append(connector);
                    }
                    s.append(str);
                }                
            }            
         }
        return s.toString();
    }
    public static void main(String[] args)
    {
        String input = args[0];
        input = input.replaceAll(" ", "");
        String[] inputArray = input.split(",");
        CyclicWordsKata kata = new  CyclicWordsKata(inputArray);
        List<List<String>> processed = kata.process();
        for(int i = 0;i<processed.size(); i++){
            if(processed != null){
               System.out.println("["+ kata.joinStringArray(processed.get(i), ",")+"]");
            }            
        }
    }
}
