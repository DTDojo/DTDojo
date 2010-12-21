# To change this template, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'word_game'
require 'word_list'

class TestWordGame < Test::Unit::TestCase
  T1_SW = 'ab'
  T1_EW = 'ef'
  T1_DICT = ['ab','b','bf','ef']

  T1_SW_LIST = ['ab.','.ab','a.b','.b','a.','b','a']
  T1_SW_RX = /^ab.$|^.ab$|^a.b$|^.b$|^a.$|^b$|^a$/i

  T2_SW = 'aa'
  T2_EW = 'zz'
  T2_DICT = ['aa','ab','db','dz','zz','az']

  T3_EW = 'zz'

  T4_SW = 'tree'
  T4_EW = 'leaf'
  T4_DICT = ["tree", "bree", "brae", "brad", "bead", "lead", "leaf"]

  def test_leaf
        assert_equal(T4_DICT, WordGame.new(T4_SW,T4_EW,T4_DICT).final_list)
  end
  def test_start_word
    assert_equal(T1_SW, WordGame.new(T1_SW,T1_EW,T1_DICT).s_word)
  end

  def test_end_word
    assert_equal(T1_EW, WordGame.new(T1_SW,T1_EW,T1_DICT).e_word)
  end

  def test_start_word_list
    assert_equal(T1_SW_LIST, WordGame.to_regexp_list(T1_SW))
  end

  def test_start_regexp
    assert_equal(T1_SW_RX, WordGame.to_regexp(WordGame.to_regexp_list(T1_SW)))
  end

  def test_dictionary
    assert_equal(T1_DICT, WordGame.new(T1_SW,T1_EW,T1_DICT).dictionary)
  end
  
  def test_single_path
    wg = WordGame.new(T1_SW,T1_EW,T1_DICT)
    assert_equal(T1_DICT,wg.final_list)
  end

  def test_single_path_length
    puts "Testing Double Path:"
    wg = WordGame.new(T1_SW,T1_EW,T1_DICT)
    assert_equal(4,wg.final_list.length)
    puts "End Test..."
  end

  def test_double_path_length
    wg = WordGame.new(T1_SW,T3_EW,T2_DICT)
    assert_equal(3,wg.final_list.length)
  end

  def test_bad_end
    wg = WordGame.new(T1_SW,T3_EW,T1_DICT)
    assert_equal(0,wg.final_list.length)
  end

end
