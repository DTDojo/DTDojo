#!/usr/bin/env ruby

require 'test/unit'
require 'blockreverse'

class BlockReverseTest < Test::Unit::TestCase

  def test_reverse_empty
    assert_equal( [], block_reverse( [] ))
  end

  def test_reverse_delimiter_only
    assert_equal( [-1,-1], block_reverse( [-1,-1] ))
  end

 def test_reverse_singleblock
    assert_equal( [1,2,3], block_reverse( [1, 2, 3] ) )
  end

  def test_reverse_two_blocks
    assert_equal( [4,5,6,-1,1,2,3], block_reverse( [1,2,3,-1,4,5,6] ))
  end

  def test_block_surrounded_by_delims
    assert_equal( [-1,-1,6,8,10,-1],block_reverse( [-1,6,8,10,-1,-1] ))
  end

  def test_small_block
    assert_equal( [-1,3,-1,2,-1,1], block_reverse( [1,-1,2,-1,3,-1] ))
  end

end
