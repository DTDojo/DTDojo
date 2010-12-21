class Bowler

  def initialize()
    @rolls = [0] * 21
    @cursor = 0
  end

  def rolls(ball)
    @rolls[@cursor] = ball
    @cursor += 1
  end

  def total()
    sum = 0
    frame_idx = 0
    10.times do 
      if strike?(frame_idx)
        sum += 10 + @rolls[frame_idx + 1] + @rolls[frame_idx + 2]
        frame_idx += 1
      elsif spare?(frame_idx)
        sum += @rolls[frame_idx] + @rolls[frame_idx+1] + @rolls[frame_idx+2]
        frame_idx += 2
      else
        sum += @rolls[frame_idx] + @rolls[frame_idx + 1]
        frame_idx += 2
      end
    end
    sum
  end

  def spare?(frame_idx)
    @rolls[frame_idx] + @rolls[frame_idx+1] == 10
  end

  def strike?(frame_idx)
    @rolls[frame_idx] == 10
  end

end

