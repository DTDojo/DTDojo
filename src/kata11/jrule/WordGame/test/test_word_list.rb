# To change this template, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'word_list'

TEST1 = ['my','word','list']

class TestWordList < Test::Unit::TestCase

  def test_foo
    #TODO: Write test
    assert_equal(TEST1, WordList.new(TEST1).words)
  end
end
