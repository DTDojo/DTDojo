import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Nov 12, 2010
 * Time: 8:35:56 PM
 * To change this template use File | Settings | File Templates.
 */
public class Family implements Comparable {
    private String _lastName = null;
    public Integer numberOfMembers = 0;
    public List<String> members;
    public Family(String lastName){
        this._lastName = lastName;
        members = new ArrayList<String>();
    }
    public void addMemeber(String name)
    {
        members.add(name);
        numberOfMembers++;
    }
    public int compareTo(Object fm)
    {
        return -1*this.numberOfMembers.compareTo(((Family)fm).numberOfMembers);
    }
}
