require 'test/unit'
require 'wordgame'

class Testwordgame < Test::Unit::TestCase

  def setup
    @game = WordGame.new()
  end

  def teardown
    @game = nil
  end

  def testCreateFourWordMap
    @game = WordGame.new()
    map = @game.createNodeMap("../fourletterwords.txt")
    assert map != nil
  end
  def testCreateFiveWordMap
    map = @game.createNodeMap("../fiveletterwords.txt")
    assert map != nil
  end
  def testSameFourWords
    path = @game.getPath('test', 'test')
    assert path.length == 1
  end
  def testSameFiveWords
    path = @game.getPath('place', 'place')
    assert path.length == 1
  end
  def testUnion
    test = @game.getAdjacentNodes("test", ['text', 'pest'], ['turn', 'text'])
    assert test.length == 2
  end
  def testGame
    test = @game.getPath('boat', 'toad')
    assert test.length == 4
    assert test == ['boat', 'goat', 'goad', 'toad']
  end
  def testLongGame
      test = @game.getPath('slams', 'sonly')
      assert test.length == 10
    end
end