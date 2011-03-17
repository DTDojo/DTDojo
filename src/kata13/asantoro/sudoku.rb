#!/usr/bin/ruby

# representation of a tile

class Tile

  attr_accessor :candidates

  def initialize(initial_value) 
    @candidates = initial_value
  end

  def remove_values( *vals ) 
    @candidates = @candidates - vals 
  end

  # returns the tile's value
  def value()
    ( @candidates.size == 1 ) ? @candidates[0] : nil
  end

  def value=(single_val)
    @candidates = [ single_val ]
  end

  def values
    @candidates
  end

  def to_s() 
    ( value().nil? ) ? " _ " :  " #{value} "
  end

end

# representation of a group
class Group

   attr_reader :tiles

   def initialize(alphabet) 
        @tiles = []
        @candidates = alphabet
    end

    # adds a tile to this group
    def add_tile(tile)
        @tiles.push(tile)
    end
    
    #updates the state of the current group.
    # return true if changes were made
    def update()
      rule1 = remove_assigned_values
      rule2 = assign_tiles_with_only_candidates
      rule1 && rule2
    end

    def done?()
      @candidates.size == 0
    end


    def remove_assigned_values
      changed = false
      assigned_values = []
      @tiles.each { |t| assigned_values << t.value if t.value }
      unsolved_tiles = @tiles.select { |t| t.value.nil? }
      unsolved_tiles.each do |tile|
        if (tile.candidates & assigned_values).length > 0
          tile.candidates = tile.candidates-assigned_values
          changed = true
        end
      end
      changed
    end

    def assign_tiles_with_only_candidates
      changed = false
      @candidates.each do |val|
        count = 0
        tile = nil
        @tiles.each do |t|
          if t.values().include? val
            count += 1
            tile = t
          end
        end
        if (count == 1) && (tile.value.nil?)
          tile.value= val
          changed = true
        end
      end
      changed
    end

end

# representation of a board
class Board

    
    #creates a board
    def initialize()
        alphabet = [ 1, 2, 3, 4, 5, 6, 7, 8, 9 ]

        #creates tiles
        @tiles = [ ]
        (0..8).each do |row|
           @tiles[row] = [ ]
           (0..8).each do |column|
               @tiles[row][column] = Tile.new(alphabet.clone)
           end
        end

        @groups = [ ]
        # creates row & column groups and associates them with tiles
        (0..8).each do |i|
            rowgroup = Group.new(alphabet)
            columngroup = Group.new(alphabet)
            (0..8).each do |j|
                rowgroup.add_tile(@tiles[i][j])
                columngroup.add_tile(@tiles[j][i])
            end
            @groups << rowgroup
            @groups << columngroup
        end
       
        # creates block groups and associates it with tiles.
        (0..2).each do |grouprow|
            (0..2).each do |groupcolumn|
                group = Group.new(alphabet)
                deltaX = 3*grouprow
                deltaY = 3*groupcolumn
                (0..2).each do |x|
                    (0..2).each do |y|
                       group.add_tile(@tiles[x+deltaX][y+deltaY])
                    end
                 end
                @groups << group
             end
        end
    end
  
    # returns a string representation of the board.
    def to_s()
      result = ""
      (0..8).each do |row|
        (0..8).each do |column|
          result += @tiles[row][column].to_s()
        end
        result += "\n"
      end
      return result
    end

    #solves the puzzle
    def solve()
        change = true
         i = 0
        while (change)
            i += 1
            change = false
            @groups.each { |group| change = group.update() || change }
            puts "iteration #{i} ----------------------"
            puts to_s()
            gets()
        end
    end

    # loads a table from the input
    def load() 
       puts "input board..."
       (0..8).each do |i|
           data = gets().chomp().split(//)
           (0..8).each { |j| @tiles[i][j].value = data[j].to_i unless data[j] == 'x' }
       end   
       puts "Thank you!"
    end
    
end

if $0 == __FILE__
  b = Board.new()
  b.load
  b.solve
end


