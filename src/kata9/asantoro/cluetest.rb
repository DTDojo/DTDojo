#!/usr/bin/ruby

require 'test/unit'
require 'cluegame'

class GameTest < Test::Unit::TestCase

  def setup
    @game = Game.new(['John Wilkes Booth'], ['gun'], ['theater'])
  end

  def test_bad_person
    assert_equal( "Peter Potamus", @game.is_it?('Peter Potamus', 'gun', 'theater') )
  end

  def test_bad_weapon
    assert_equal( "rope", @game.is_it?('John Wilkes Booth', 'rope', 'theater') )
  end

  def test_bad_place
    assert_equal( "park", @game.is_it?('John Wilkes Booth', 'gun', 'park') )
  end

  def test_two_bad
    assert( ["rope", "park"].include?( @game.is_it?('John Wilkes Booth', 'rope', 'park') ) )
    assert( ["Peter Potamus", "park"].include?( @game.is_it?('Peter Potamus', 'gun', 'park') ) )
    assert( ["Peter Potamus", "rope"].include?( @game.is_it?('Peter Potamus', 'rope', 'theater') ) )
  end

  def test_all_correct
    assert_nil( @game.is_it?( 'John Wilkes Booth', 'gun', 'theater' ) )
  end

end

class UserTest < Test::Unit::TestCase

  def setup
    @game = Game.new(['John Wilkes Booth'],['gun'],['theater'])
  end

  def test_can_solve_simple
    suspects = [ 'John Wilkes Booth', 'Charles Guiteau', 'Lee Harvey Oswald']
    weapons  = [ 'rifle', 'revolver', 'gun' ]
    locations = [ 'grassy knoll', 'theater', 'train station' ]
    player = Player.new(@game, suspects, weapons, locations)
    assert_equal("John Wilkes Booth with the gun in the theater", player.solve)
  end

  def test_can_solve_many
  end

end

