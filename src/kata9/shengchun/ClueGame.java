package DTDojo.src.kata9.shengchun;

import java.util.List;
import java.util.Arrays;


public class ClueGame {
    private Long _whoIndex, _weaponIndex, _placeIndex;
    private List<String> _whoList,_weaponList, _placeList;
    public ClueGame(List<String> whoList, List<String> weaponList, List<String> placeList){
         _whoList = whoList;
        _weaponList = weaponList;
        _placeList = placeList;
    }
    public void generateClue()
    {
        _whoIndex = Math.round(_whoList.size()*Math.random());
        _weaponIndex = Math.round(_weaponList.size()*Math.random());
        _placeIndex = Math.round(_placeList.size()*Math.random());
        _whoIndex -= _whoIndex == _whoList.size()?1:0;
        _weaponIndex -= _weaponIndex == _weaponList.size()?1:0;
        _placeIndex -= _placeIndex == _placeList.size()?1:0;
        System.out.println(_whoIndex + " " + _weaponIndex + " " + _placeIndex);
    }
    public int rightGuess(int type, int value)
    {
        Long compareToValue;
        switch (type)
        {
          case 1:
              compareToValue = _whoIndex;
              break;
          case 2:
              compareToValue = _weaponIndex;
              break;
          case 3:
              compareToValue  = _placeIndex;
              break;
          default:
              compareToValue  =  0L;
        }
        return (new Long(value)).compareTo(compareToValue);
    }
    public String isIt(String who, String weapon, String place){
        if(rightGuess(1,_whoList.indexOf(who)) != 0 )
        {
            return who;
        }
        if(rightGuess(2,_weaponList.indexOf(weapon)) != 0 )
        {
            return weapon;
        }
        if(rightGuess(3,_placeList.indexOf(place)) != 0 )
        {
            return place;
        }
        return null;
    }
    public String figureOutClue(){

        String who = _whoList.get(getIndex(_whoList.size(),1));
        String weapon = _weaponList.get(getIndex(_weaponList.size(),2));
        String place = _placeList.get(getIndex(_placeList.size(),3));
        return who + " with " + weapon + " at " + place;
    }
    public int getIndex(int size, int type){
        int lower = 0;
        int upper = size -1;
        int guessValue = (lower + upper)/2;
        while(rightGuess(type,guessValue) != 0){
            if(rightGuess(type,guessValue) < 0 ){
               lower = upper-lower == 1 ?upper:guessValue;
            }
            else
            {
                upper = guessValue;
            }
            guessValue = (lower + upper)/2;
        }
        return guessValue;
    }
    public static void main(String argv[]){
        List<String> who, weapon, place;
         if(argv.length == 3 ){
             who = Arrays.asList(argv[0].split(","));
             weapon =  Arrays.asList(argv[1].split(","));
             place = Arrays.asList(argv[2].split(","));
         }
        else
         {
             who = Arrays.asList("Van Helsing", "Wyatt Earp");
             weapon = Arrays.asList("stake", "gun");
             place = Arrays.asList("cemetery", "OK Corral");
         }
        ClueGame cg = new ClueGame(who, weapon, place);
        cg.generateClue();        
        System.out.println(cg.isIt(who.get(0), weapon.get(0), place.get(0)));
        System.out.println(cg.figureOutClue());
    }
}
