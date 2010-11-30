
class Array

  def random_element
    self[ rand(self.length) ]
  end

end

class Game

  def initialize(suspects,weapons,locations)
    @suspect = suspects.random_element
    @weapon = weapons.random_element
    @location =  locations.random_element
  end

  def is_it?(suspect, weapon, location)
    bad_values = [ ]
    bad_values << suspect  if suspect != @suspect
    bad_values << weapon   if weapon != @weapon
    bad_values << location if location != @location
    bad_values.random_element
  end

end

class Player

  def initialize(game, suspects, weapons, locations)
    @game = game
    @suspects = suspects
    @weapons = weapons
    @locations = locations
  end

  def solve
    suspect_idx, weapon_idx, location_idx = 0,0,0
    suspect, weapon, location = get_candidates(suspect_idx,weapon_idx,location_idx)
    solution = @game.is_it?(suspect,weapon,location)
    while ( solution != nil) do 
      suspect_idx += 1 if solution == @suspects[suspect_idx]
      weapon_idx += 1 if solution == @weapons[weapon_idx]
      location_idx += 1 if solution == @locations[location_idx]
      suspect, weapon, location = get_candidates(suspect_idx, weapon_idx, location_idx)
      solution = @game.is_it?(suspect,weapon,location)
    end
    return "#{suspect} with the #{weapon} in the #{location}"
  end

  def get_candidates(s, w, l)
    return @suspects[s], @weapons[w], @locations[l]
  end

end
