
def secret_santa(person_list)
  return { } if person_list.length == 0
  permutations(person_list) do |santa_list|
    good_solution = true
    santa_list.each_index do |idx|
      person, santa = person_list[idx], santa_list[idx]
      if related(person,santa) 
        good_solution = false
        break
      end
    end
    # if we get here, the permutation is good,
    # so we can return the result
    return make_map(person_list, santa_list) if good_solution
  end
  raise "no valid solution found"  
end

def permutation_it(current, rest, proc)
  if rest.empty? 
    proc.call(current)   
  else
    rest.each do |val|
      permutation_it( current + [ val ], rest-[val], proc )
    end
  end
end

def permutations(list, &proc)
  permutation_it([], list, proc)
end

#def permutations(list)
#  return [ [] ] if list.empty?
#  perms = [ ]
#  list.each do |x|
#    rest = list - [ x]
#    permutations(rest).each do |permutation|
#      perms << permutation + [x]
#    end
#  end
#  return perms
#end

def make_map(keys,values)
  raise "keys and values are not the same size!" if keys.length != values.length
  map = { }
  keys.length.times do |i|
    map[keys[i]] = values[i]
  end
  return map
end

def related(person1, person2)
  return (person1.split)[1]  == (person2.split)[1]
end
