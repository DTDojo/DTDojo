require 'test/unit'
require 'wordgame'

class DistanceTest < Test::Unit::TestCase

  def test_distance
    assert_equal(1, "abcd".distance("bbcd") )
    assert_equal(1, "abcd".distance("axcd") )
    assert_equal(1, "a".distance("b") )
    assert_equal(2, "abcd".distance("xbxd") )
    assert_equal(3, "abcd".distance("axxx") )
    assert_equal(4, "abcd".distance("xxxx") )
  end

  def test_zero
    assert_equal(0, "abcd".distance("abcd"))
    assert_equal(0, "".distance(""))
  end

  def test_different_size_throws_exception
    assert_raise RuntimeError do
      "abc".distance("abcd")
    end
  end

end

class PreCalcGraphTest < Test::Unit::TestCase

  def test_one_node_graph
    graph = PreCalcGraph.new( [ 'abc' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(1, graph.size )
    assert_equal( [ ] , graph['abc'] )
  end

  def test_two_distinct_nodes
    graph = PreCalcGraph.new( [ 'abc', 'cde' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(2, graph.size)
    assert_equal( [], graph['abc'] )
    assert_equal( [], graph['cde'] )
  end

  def test_two_with_distance_one
    graph = PreCalcGraph.new( [ 'abc', 'axc'] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(2, graph.size)
    assert_equal( [ 'axc' ], graph['abc'] )
    assert_equal( [ 'abc' ], graph['axc'] )
  end

  def test_with_three_distance_one
    graph = PreCalcGraph.new( [ 'abc', 'axc', 'ayc' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(3, graph.size)
    assert_equal( ['axc', 'ayc'], graph['abc'] )
    assert_equal( ['abc', 'ayc'], graph['axc'] )
    assert_equal( ['abc', 'axc'], graph['ayc'] )
  end

  def test_mixed_graph
    graph = PreCalcGraph.new( [ 'abc', 'axc', 'ayc', 'def', 'dxf', 'ghi' ] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal(6, graph.size)
    assert_equal( ['axc', 'ayc'], graph['abc'] )
    assert_equal( ['abc', 'ayc'], graph['axc'] )
    assert_equal( ['abc', 'axc'], graph['ayc'] )
    assert_equal( [ 'dxf' ], graph['def'] )
    assert_equal( [ 'def' ], graph['dxf'] )
    assert_equal( [ ], graph['ghi'] )
  end

  def test_simple_game
    graph = PreCalcGraph.new( ['boot', 'boat'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( ['boot', 'boat'], graph.shortest_path('boot', 'boat') )
  end

  def test_two_step_path
    graph =PreCalcGraph.new( ['abc', 'xbc', 'xby'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( ['xby', 'xbc', 'abc'], graph.shortest_path('xby', 'abc') )
  end

  def test_unsolvable
    graph = PreCalcGraph.new( ['abc', 'xbc', 'xby'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( [ ], graph.shortest_path('abc', 'fgh'))
  end

end

class CalcGraphTest < Test::Unit::TestCase

  def test_one_node_graph
    graph = CalcGraph.new( [ 'abc' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(1, graph.size )
    assert_equal( [ ] , graph['abc'] )
  end

  def test_two_distinct_nodes
    graph = CalcGraph.new( [ 'abc', 'cde' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(2, graph.size)
    assert_equal( [], graph['abc'] )
    assert_equal( [], graph['cde'] )
  end

  def test_two_with_distance_one
    graph = CalcGraph.new( [ 'abc', 'axc'] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(2, graph.size)
    assert_equal( [ 'axc' ], graph['abc'] )
    assert_equal( [ 'abc' ], graph['axc'] )
  end

  def test_with_three_distance_one
    graph = CalcGraph.new( [ 'abc', 'axc', 'ayc' ] ) { |n1,n2| n1.distance(n2)==1 }
    assert_equal(3, graph.size)
    assert_equal( ['axc', 'ayc'], graph['abc'] )
    assert_equal( ['abc', 'ayc'], graph['axc'] )
    assert_equal( ['abc', 'axc'], graph['ayc'] )
  end

  def test_mixed_graph
    graph = CalcGraph.new( [ 'abc', 'axc', 'ayc', 'def', 'dxf', 'ghi' ] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal(6, graph.size)
    assert_equal( ['axc', 'ayc'], graph['abc'] )
    assert_equal( ['abc', 'ayc'], graph['axc'] )
    assert_equal( ['abc', 'axc'], graph['ayc'] )
    assert_equal( [ 'dxf' ], graph['def'] )
    assert_equal( [ 'def' ], graph['dxf'] )
    assert_equal( [ ], graph['ghi'] )
  end

  def test_simple_game
    graph = CalcGraph.new( ['boot', 'boat'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( ['boot', 'boat'], graph.shortest_path('boot', 'boat') )
  end

  def test_two_step_path
    graph =CalcGraph.new( ['abc', 'xbc', 'xby'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( ['xby', 'xbc', 'abc'], graph.shortest_path('xby', 'abc') )
  end

  def test_unsolvable
    graph = CalcGraph.new( ['abc', 'xbc', 'xby'] ) { |n1,n2| n1.distance(n2) == 1 }
    assert_equal( [ ], graph.shortest_path('abc', 'fgh'))
  end

end
