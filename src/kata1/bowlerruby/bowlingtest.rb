#!/usr/bin/ruby

require "test/unit"
require "objbowler"

# tests for the bowling game

class BowlerTest < Test::Unit::TestCase

  def test_simple
    george = Bowler.new()
    10.times do 
      george.rolls(1)
      george.rolls(3)
    end
    assert_equal(40, george.total() )
  end

  def test_perfect_game
    george = Bowler.new()
    12.times { george.rolls(10) } # 10 frames + 2 extra balls
    assert_equal(300, george.total())
  end

  def test_spares
    george = Bowler.new()
    10.times do 
      george.rolls(9)
      george.rolls(1)
    end
    george.rolls(7)
    assert_equal(188, george.total())
  end

  def test_spare_with_tens
    george = Bowler.new()
    10.times do
      george.rolls(0)
      george.rolls(10)
    end
    george.rolls(7)
    assert_equal(107, george.total())
  end

end
