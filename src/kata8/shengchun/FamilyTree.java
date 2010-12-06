import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Nov 21, 2010
 * Time: 9:22:55 AM
 * Input a list of parent child relationship
 * and find relationship between a pair of person
 */
public class FamilyTree {
    Map<String, Integer> rank = new HashMap<String, Integer>();
    private List<String> _relationList;
    Map<String, List<String>> parentForChild = new HashMap<String, List<String>>();
    Map<String, List<String>> childForParent = new HashMap<String, List<String>>();
    List<String> notInTree = new ArrayList<String>();
    List<String> notProcessedPair = new ArrayList<String>();
    public FamilyTree(List<String> relationList){
        _relationList = relationList;
    }
    public void processRelation() throws Exception {
        List<String> newlyAdded = new ArrayList<String>();
        for(String pair: _relationList){
            String[] members = pair.split(":");
            String parent = members[0];
            String child = members[1];
            if(rank.size() == 0 ){
                rank.put(parent, 0);
                rank.put(child,1);
                addRelationShipMap(parent,child);
            }
            else
            {
                Integer parentRank = rank.get(parent);
                Integer childRank = rank.get(child);
                if(parentRank != null)
                {
                    //both parent and child are already in the rank map
                    //confirm they have the correct relationship
                    if(childRank != null){
                        //right rank relationship
                        //add parent/child relationship
                         if(parentRank - childRank == -1){
                             addRelationShipMap(parent,child);
                         }else
                         {
                             throw new Exception("relation conflict");
                         }
                     }
                    else
                    {
                        rank.put(child,parentRank + 1);
                        addRelationShipMap(parent,child);
                        //just add new rank for child
                        //see whether this child appeared before
                        //if yes, add relationship for the child
                        if(notInTree.contains(child)){
                            newlyAdded = addRankForUnprocessed(child, parentRank + 1);
                            notProcessedPair.removeAll(newlyAdded);
                            newlyAdded.clear(); 
                            notInTree.remove(child);
                        }

                    }
                }
                else if(childRank != null)
                {
                    rank.put(parent, childRank -1);
                    addRelationShipMap(parent,child);
                    if (notInTree.contains(parent)) {
                        newlyAdded = addRankForUnprocessed(parent, childRank -1);
                        notProcessedPair.removeAll(newlyAdded);
                        newlyAdded.clear();                        
                        notInTree.remove(parent);
                    }
                }
                else
                {
                    addRelationShipMap(parent,child);
                    notInTree.add(parent);
                    notInTree.add(child);
                    notProcessedPair.add(pair);
                }
            }
        }
        while(notProcessedPair.size() >0 ){
            List<String> entriesToRemove = new ArrayList<String>();
            for(int i = 0;i < notProcessedPair.size();i++){
                String pair = notProcessedPair.get(i);
                String[] pairs = pair.split(":");
                String p = pairs[0];
                String c = pairs[1];
                if (rank.get(p) != null && rank.get(c) != null) {
                    continue;
                }
                if(rank.get(p) != null){
                    rank.put(c,rank.get(p)+1);
                    entriesToRemove.add(pair);
                }
                else if(rank.get(c) != null){
                    rank.put(c,rank.get(p)+1);
                    entriesToRemove.add(pair);
                }
            }
            notProcessedPair.removeAll(entriesToRemove);
            //an not resolve any pair
            if(entriesToRemove.size() == 0 )
            {
                 break;
            }
        }
    }
    public String getRelationship(String p1, String p2)
    {
        Integer rank1 = rank.get(p1);
        Integer rank2 = rank.get(p2);
        if((parentForChild.get(p1) == null && childForParent.get(p1)== null) || (parentForChild.get(p2) == null && childForParent.get(p2)== null) ){
             return "STRANGER" ;
        }
        if(rank1 == rank2 ){
            if(parentForChild.get(p1).equals(parentForChild.get(p2))){
                return "SIBLING";
            }
            if(parentForChild.get(parentForChild.get(p1).get(0)).get(0).equals(parentForChild.get(parentForChild.get(p2).get(0)).get(0))){
                return "COUSIN";
            }
            else
            {
                return "REELATED";
            }
        }
        if(rank1 -rank2 == -1){
            if(parentForChild.get(p2).get(0).equals(p1)){
                return "PARENT";
            }
            if(parentForChild.get(p1).get(0).equals(parentForChild.get(parentForChild.get(p2).get(0)).get(0))){
                return "UNCLE/AUNT";
            }
            else
            {
                return "RELATED";
            }
        }
        if(rank1 -rank2 == 1){
            if(parentForChild.get(p1).get(0).equals(p2)){
                return "CHILD";
            }
            if(parentForChild.get(p2).get(0).equals(parentForChild.get(parentForChild.get(p1).get(0)).get(0))){
                return "NEPHEW/NIECE";
            }
            else
            {
                return "RELATED";
            }
        }
        if(rank1 -rank2 == -2){

            if(p1.equals(parentForChild.get(parentForChild.get(p2).get(0)).get(0))){
                return "GRANDPARENT";
            }
            else
            {
                return "RELATED";
            }
        }
        if(rank1 -rank2 == 2){

            if(p2.equals(parentForChild.get(parentForChild.get(p1).get(0)).get(0))){
                return "GRANDCHILD";                
            }
            else
            {
                return "RELATED";
            }
        }
        else
        {
            return "RELATED";
        }

    }
    private void addRelationShipMap(String parent, String child){
        List<String> parentList =  parentForChild.get(child);
        if(parentList == null){
            parentList = new ArrayList<String>();
        }
        parentList.add(parent);
        parentForChild.put(child,parentList);
        List<String> childList =  childForParent.get(parent);
        if(childList == null){
            childList = new ArrayList<String>();
        }
        childList.add(child);
        childForParent.put(parent,childList);
    }
    private List<String> addRankForUnprocessed(String person, int referenceRank)
    {
        List <String> newlyAdded = new ArrayList<String>();
        for(String pair: notProcessedPair){
             String[] persons = pair.split(":");
            String parent = persons[0];
            String child = persons[1];
            if(parent.equals(person) || child.equals(person)){
                if (parent.equals(person)) {
                    rank.put(child, referenceRank + 1);                    
                } else {
                    rank.put(parent, referenceRank - 1);
                }
                newlyAdded.add(pair);
            }
        }
        return newlyAdded;
    }
}
