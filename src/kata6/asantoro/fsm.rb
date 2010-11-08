class FiniteStateMachine

  attr_reader :state

  def initialize(alphabet, states, transitions)
    @alphabet = alphabet.split(',')
    @state, @state_value = create_states(states)
    @transition = create_transitions(transitions)
  end

  def eval(input)
    input.split(//).each do |symbol|
      raise "invalid symbol" unless @alphabet.include?(symbol)
      @state = @transition["#{@state}:#{symbol}"] || @state
    end
    @state_value[@state]
  end

  def state_value
    @state_value[@state]
  end

  def create_states(states)
    state_value = { } 
    init_state = nil
    states.split(',').each do |state_with_val|
      state,value = state_with_val.split(/:/)
      init_state ||= state
      state_value[state] = value
    end
    return init_state, state_value
  end

  def create_transitions(transition_str)
    transitions = { }
    transition_str.split(/,/).each do |transition|
      state,new_state,symbol = transition.split(/:/)
      transitions["#{state}:#{symbol}"] = new_state
    end    
    return transitions
  end

end

