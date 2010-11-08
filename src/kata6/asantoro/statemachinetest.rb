require 'test/unit'
require 'fsm'

class FsmTest < Test::Unit::TestCase

  def setup
    @fsm = FiniteStateMachine.new("0,1","A:inA,B:inB","A:B:0,B:A:1")
  end

  def test_initial_state
    assert_equal("A", @fsm.state)
    assert_equal("inA", @fsm.state_value)
  end

  def test_transition
    assert_equal("inB", @fsm.eval("0"))
    assert_equal("inA", @fsm.eval("1"))
    assert_equal("inA", @fsm.eval("1"))    
  end

  def test_transition_sequences
    assert_equal("inA", @fsm.eval("01"))
    assert_equal("inA", @fsm.eval("10101"))
  end

  def test_bad_symbols
    assert_raise RuntimeError do
      @fsm.eval("01x")
    end
  end

end
