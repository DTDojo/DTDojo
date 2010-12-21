import java.util.*;

/**
 * Find Secret Santa for a list of names
 * User: sye
 * Date: Nov 12, 2010
 * Time: 4:52:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SecretSantaFinder {
    Map<String, Family> familyMembers = new HashMap <String, Family>  ();
    int totalNames = 0;
    int maxFamilyMembers = 0;

    public SecretSantaFinder(String[] lists){
        String name;
        String[] names;
        Family family;
         for(int i=0;i< lists.length;i++)
         {
             name = lists[i].trim();
             name = name.replaceAll("  "," ");
             names = name.split(" ");
             if(familyMembers.get(names[1]) != null )
             {
                 family = familyMembers.get(names[1]);
             }
             else
             {
                 family = new Family(names[1]);
             }
             family.addMemeber(name);
             totalNames++;
             if(maxFamilyMembers < family.numberOfMembers)
             {
                 maxFamilyMembers = family.numberOfMembers;
             }
             familyMembers.put(names[1], family);
         }
    }
    public Map<String, String>  pair()
    {
        List<Family> families = new ArrayList<Family>();
        //if one family has more than half of the total names, it is impossible to get a solution
        if(2*maxFamilyMembers > totalNames){
           return null;
        }
        for(String key: familyMembers.keySet())
        {
             families.add(familyMembers.get(key)) ;
        }
        Collections.sort(families);
        List<String> receiveList = new ArrayList<String>(totalNames);
        List<String> sendList = new ArrayList<String>(totalNames);
        List<String> largestFamilyMembers = new ArrayList<String>();
        Map<String, String> secretList = new HashMap<String,String>();
        for(int i=0;i < families.size();i++)
        {
             List<String> names = families.get(i).members;
             receiveList.addAll(names);
             if(i== 0)
             {
                 largestFamilyMembers = names;
             }
             else
             {
                 sendList.addAll(names);
             }
        }
        sendList.addAll(largestFamilyMembers);
        for(int i=0;i < sendList.size();i++)
        {
               secretList.put(sendList.get(i), receiveList.get(i));
        }
        return secretList;
    }
    public static void  main(String[] args)
    {
        String[] names = args[0].split(",");
        SecretSantaFinder ssf = new SecretSantaFinder(names);
        Map<String,String> pairs = ssf.pair();
        if(pairs == null)
        {
            System.out.println("no match possible");

        }
        else
        {
            for(String key: pairs.keySet())
            {
                System.out.println(key + ":" + pairs.get(key));
            }
        }
    }
}
