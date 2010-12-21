# To change this template, choose Tools | Templates
# and open the template in the editor.

require 'word_list'

class WordGame
  attr_reader :s_word
  attr_reader :e_word

  attr_reader :dictionary
  attr_reader :final_list

  def WordGame.to_regexp_list(w)
    l = [ w + '.' ]
    l += w.split(//).each_index.collect {|i| t = w.dup; t.insert(i,'.')}
    l += w.split(//).each_index.collect {|i| w[0,i] + '.' + w[i+1,w.length].to_s }
    l += w.split(//).each_index.collect {|i| w[0,i] + w[i+1,w.length].to_s }
  end

  def WordGame.to_regexp(l)
    Regexp.new('^' + l.join('$|^') + '$','i')
  end

#  def to_s
#    puts [@s_word] + @connect_list
#  end
  
  def connect_words(start_list)
    puts '----------------------------------------------------------------'
    puts start_list.inspect
    next_level = {}
    start_list.each do |parrent, children|
      p_list = eval(parrent.to_s)
      dict = @dictionary - p_list
      children.each do |word|
        cached = @cache[word]
        if cached == nil
          re = WordGame.to_regexp(WordGame.to_regexp_list(word))
          found = dict.select { |w| w != word and w =~ re }
          @cache[word] = found if cached == nil
        else
          found =  cached
        end
        if found & [@e_word] != []
          @final_list = p_list + [word,@e_word]
          return
        else
          new_parent = p_list + [word]
          next_level[new_parent.inspect.to_sym] = found
        end
      end
    end
    connect_words(next_level) if @final_list == []
  end
  
  def initialize(s_word, e_word, dictionary)
    @s_word = s_word
    @e_word = e_word
    @dictionary = dictionary
    @cache = {}
    @final_list = []
    re = WordGame.to_regexp(WordGame.to_regexp_list(@e_word))
    found = @dictionary.select {|w| w =~ re}
    connect_words({'[]'.to_sym => [@s_word]}) unless found.empty?
  end
end
