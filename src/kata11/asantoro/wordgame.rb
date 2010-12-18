
class String

  attr_accessor :previous

  def distance(other_word)
    raise "incompatible word lengths" if self.length != other_word.length
    d = 0
    self.length.times do |i|
      d += 1 if self[i] != other_word[i]
    end
    return d
  end

end

module GraphAlgorithms

  def shortest_path(from_node, to_node)
    previous_node_map = { }
    previous_node_map[from_node] = nil
    nodes_to_visit = [ from_node ]
    while ! nodes_to_visit.empty? do
      current_node = nodes_to_visit.shift
      next_nodes = self[current_node]
      next if next_nodes.nil?
      next_nodes.each do |next_node|
        if ! previous_node_map.has_key?(next_node)
          previous_node_map[next_node] = current_node
          nodes_to_visit << next_node 
          return build_path(to_node, previous_node_map) if next_node == to_node
        end
      end
    end
    return [ ]
  end

  def build_path(to_node, previous_node_map)
    path = [ to_node ]
    node = previous_node_map[to_node]
    while ! node.nil?
      path.unshift(node)
      node = previous_node_map[node]
    end
    return path
  end

end



class PreCalcGraph 
 
  include GraphAlgorithms

  def initialize( node_list )
    @node_map = { }
    node_list.each do |node|
      @node_map[node] = [ ]
      node_list.each do |second_node|
        @node_map[node] << second_node if yield node, second_node
      end
    end
  end

  def size
    @node_map.size()
  end

  def [](key)
    @node_map[key]
  end

end

class CalcGraph 

  include GraphAlgorithms

  def initialize( node_list, &proc )
    @node_list = node_list
    @edge_func = proc
    @edge_cache = { }
  end

  def size
    @node_list.size()
  end

  def [](from_node)
    next_nodes = @edge_cache[from_node]
    if next_nodes.nil?
      next_nodes = @node_list.select { |node| from_node.distance(node) == 1 }
      @edge_cache[from_node] = next_nodes
    end
    return next_nodes
  end

end


# The game class is able to solve the game.
class WordGame

  def initialize( dictionary ) 
    words = IO.readlines(dictionary).map { |line| line.chomp }
    @word_graph = CalcGraph.new(words) { |w1,w2| w1.distance(w2) == 1 }
  end

  # Solves the game, if possible, returning an array
  # consisting of the path between the an empty 
  # path if unusable
  # Uses Djikstra's shortest path algorithm to find
  # the path from one end to the other.
  def solve(from_word, to_word)
    return @word_graph.shortest_path(from_word, to_word)
  end

end


