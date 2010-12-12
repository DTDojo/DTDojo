#
# This is the one-parent solution.
# 
class FamilyTree

  def initialize()
    @parent_of = { }
  end

  def add(parent,child)
    @parent_of[child] = parent
  end

  def relationship(person1, person2)
    return "PARENT" if same_person?( person1, @parent_of[person2] )
    return "CHILD"  if same_person?(@parent_of[person1], person2 ) 
    return "SIBLING" if same_person?(@parent_of[person1], @parent_of[person2] )
    return "GRANDPARENT" if same_person?(person1, grandparent_of(person2))
    return "GRANDCHILD" if same_person?(grandparent_of(person1), person2)
    return "UNCLE/AUNT" if same_person?(@parent_of[person1], grandparent_of(person2))
    return "NEPHEW/NIECE" if same_person?(grandparent_of(person1), @parent_of[person2])
    return "COUSIN" if same_person?(grandparent_of(person1), grandparent_of(person2))
    return "RELATED" if has_ancestor_in_common(person1, person2)
    return "UNRELATED" 
  end

  def same_person?(p1, p2)
    (p1 == p2) && (p1 != nil)
  end

  def grandparent_of(person)
    @parent_of[@parent_of[person]]
  end

  def has_ancestor_in_common(person1, person2)
    lineage1 = lineage(person1)
    lineage2 = lineage(person2)
    common = lineage1 & lineage2
    return ! common.empty?
  end

  def lineage(person1)
    l = [ person1 ]
    parent = @parent_of[person1]
    while (parent != nil) do
       l << parent
       parent = @parent_of[parent]
    end
    l
  end
  
end
