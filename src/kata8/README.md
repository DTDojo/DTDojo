Family Finder Kata
----------------------------------

The holidays are coming and you will probably be inundated with cards and invitations from family members you barely remember. In order to figure out what your relationship to these strangers is, build a family tree program to answer the question of the relationship between any two people. The tree should be build by passing it parent child relationships and then you should be able to query the tree giving two names and it should return one of the following values:

  * PARENT
  * CHILD
  * SIBLING
  * COUSIN
  * UNCLE/AUNT
  * NEPHEW/NIECE
  * GRANDPARENT
  * GRANDCHILD
  * RELATED (part of the same family, but not one of the above relationships)
  * STRANGER 

For example:

    myFamily = FamilyTree()
    myFamily.add_parent_child("John Smith", "Charles Smith") // john is the father of charles
    myFamily.add_parent_child("John Smith", "Anna Smith")
    myFamily.add_parent_child("Anna Smith", "Peter Paul Johansen")
    
    // think "first person is the ????? of second person"
    myFamily.relationship("John Smith", "Charles Smith") --> PARENT
    myFamily.relationship("Charles Smith", "John Smith") --> CHILD
    myFamily.relationship("Peter Paul Johansen", "Charles Smith") --> NEPHEW/NIECE
    myFamily.relationship("Zachary Smith", "Charles Smith") --> STRANGER
