# To change this template, choose Tools | Templates
# and open the template in the editor.

class WordList < Array
  attr_reader :words

  def inspect
    @words.inspect
  end

  def load_file(p)
    puts 
    s = File.open(p) {|f| f.read}
    @words = @words +  s.split.to_a
  end
  
  def initialize(w)
    @words = []
    @words = w.to_a
  end
end
