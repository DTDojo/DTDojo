import org.junit.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: sye
 * Date: Nov 21, 2010
 * Time: 8:34:18 PM
 * To test FamilyTree finder class
 */
public class TestFamilyTree {
    @Test
    public void test()
    {
        List<String> relationshipList = Arrays.asList("P301:P407", "P101:P201","P101:P202","P101:P203","P101:P204","P101:P205",
                "P101:P206",
                "P201:P301",
                "P201:P302",
                "P201:P303",
                "P201:P304",
                "P201:P305",
                "P202:P306",
                "P202:P307",
                "P202:P308",
                "P202:P309",
                "P202:P310",
                "P203:P311",
                "P203:P312",
                "P203:P313",
                "P203:P314",
                "P203:P315",
                "P301:P401",
                "P301:P402",
                "P301:P403",
                "P301:P404",
                "P301:P405"
        );



        FamilyTree ft = new FamilyTree(relationshipList);
        try {
            ft.processRelation();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        assertEquals(ft.getRelationship("P101","P201"), "PARENT");
        assertEquals(ft.getRelationship("P101","P406"), "STRANGER");
        assertEquals(ft.getRelationship("P101","P309"), "GRANDPARENT");
        assertEquals(ft.getRelationship("P301","P101"), "GRANDCHILD");
        assertEquals(ft.getRelationship("P301","P315"), "COUSIN");
        assertEquals(ft.getRelationship("P301","P305"), "SIBLING");
        assertEquals(ft.getRelationship("P301","P202"), "NEPHEW/NIECE");
        assertEquals(ft.getRelationship("P203","P305"), "UNCLE/AUNT");
        assertEquals(ft.getRelationship("P101","P405"), "RELATED");
    }
}
