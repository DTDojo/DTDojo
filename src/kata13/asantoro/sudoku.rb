#!/usr/local/bin/ruby

# representation of a tile

class Tile

  attrib_accessor :candidates

  def initialize(alphabet) 
    @candidates = alphabet
  end

  # returns true if the candidate is a viable candidate
  # for this tile.
  def is_candidate?(candidate) 
     @candidates.include?(candidate)
  end

  # returns the tile's value
  def value()
    ( @candidates.size == 1 ) ? @candidates[0] : nil
  end
  
  # removes tile candidate if it exists
  def remove_candidate(candidate)
     @candidates.remove(candidate) if (@candidates.include?(candidate))
  end

  def to_s() 
    (@value) ?  " #{@value} " : " _ " 
  end

end

# representation of a group
class Group

    def initialize(alphabet) 
        @tiles = []
        @candidates = alphabet
        @group_values = [ ] 
    end

    # adds a tile to this group
    def add_tile(tile)
        @tiles.push(tile)
    end
    
    #updates the state of the current group.
    # return true if changes were made
    def update()
	remove_assigned_tiles_from_candidates
        remove_assigned_values_from_candidates
        assign_tiles_with_only_candidate
        limit_subgroups
    end
        
    def done?()
      @candidates.size == 0
    end


    def remove_assigned_tiles_from_candidates
        @assigned = []
        @tiles.each { |t| @assign.append(t.value) if t.value }
	@candidates = @candidates -@assigned
    end

    def remove_assigned_values_from_candidates
        @tiles.each do |tile|
            tile.candidates = tile.candidates & @candidates
        end
    end

    def assign_tiles_with_only_candidates
        @candidates.each do |candidate|
            count = 0;
            tile = nil;
            @tiles.each do |t| 
                if (t.is_candidate?(candidate)
                    count = count+1
                    tile = t
                end
            end
        end
    end

    def limit_subgroup
        @tiles.each do |tile|
            
        end
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
            rowgroup = Group.new()
            columngroup = Group.new()
            (0..8).each do |j|
                rowgroup.add_tile(@tiles[i][j])
                columngroup.add_tile(@tiles[j][i])
                @tiles[i][j].add_group(rowgroup)
                @tiles[j][i].add_group(columngroup)
            end
            @groups.push(rowgroup)
            @groups.push(columngroup)
        end
       
        # creates block groups and associates it with tiles.
        (0..2).each do |grouprow|
            (0..2).each do |groupcolumn|
                group = Group.new()
                deltaX = 3*grouprow
                deltaY = 3*groupcolumn
                (0..2).each do |x|
                    (0..2).each do |y|
                       group.add_tile(@tiles[x+deltaX][y+deltaY])
                       @tiles[x+deltaX][y+deltaY].add_group(group)
                    end
                 end
                @groups.push(group)
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
            @tiles.each do |tilerow|
               tilerow.each do |tile| 
                  change = tile.update() || change 
               end
            end
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
           (0..8).each { |j| @tiles[i][j].set_value(data[j].to_i) unless data[j] == 0 }
       end   
       puts "Thank you!"
    end
    

end

board = Board.new()
board.load()
board.solve()

print board.to_s()


