require 'test/unit'
require 'familytree'

class FamilyTreeTest < Test::Unit::TestCase 
  def setup
    @tree = FamilyTree.new()
    @tree.add("John Smith", "Fred Smith")
    @tree.add("Fred Smith", "Dionne Warwick")
    @tree.add("Dionne Warwick", "Johnny Warwick")
    @tree.add("John Smith", "Annie Smith")
    @tree.add("Annie Smith", "Frankie Avalon")
  end

  def test_parent_child
    assert_equal("PARENT", @tree.relationship("John Smith", "Fred Smith") )
    assert_equal("CHILD", @tree.relationship("Fred Smith", "John Smith") )
  end

  def test_siblings
    assert_equal("SIBLING", @tree.relationship("Fred Smith", "Annie Smith"))
    assert_equal("SIBLING", @tree.relationship("Annie Smith", "Fred Smith"))
  end

  def test_grandparents
    assert_equal("GRANDPARENT", @tree.relationship("John Smith", "Frankie Avalon"))
    assert_equal("GRANDCHILD",  @tree.relationship("Frankie Avalon", "John Smith"))  
  end

  def test_uncle_aunt
    assert_equal("UNCLE/AUNT", @tree.relationship("Fred Smith", "Frankie Avalon"))
    assert_equal("NEPHEW/NIECE", @tree.relationship("Frankie Avalon", "Fred Smith"))
  end

  def test_cousins
    assert_equal("COUSIN", @tree.relationship("Frankie Avalon", "Dionne Warwick"))
    assert_equal("COUSIN", @tree.relationship("Dionne Warwick", "Frankie Avalon"))
  end
 
  def test_distant_relative
    assert_equal("RELATED", @tree.relationship("Annie Smith", "Johnny Warwick"))
    assert_equal("RELATED", @tree.relationship("John Smith", "Johnny Warwick"))
  end

  def test_unrelated
    assert_equal("UNRELATED", @tree.relationship("Fred Smith", "Johann Doe"))
  end


end
