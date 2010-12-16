# BlockReverseKata solution.

def block_reverse( array ) 
  from, to = next_block(0, array)
  while (from != nil) do
    reverse(array,from,to)
    from, to = next_block(to+1,array)
  end
  reverse(array,0,array.length-1)
end

def next_block(index, array)
  start_idx = first_non_negative(index,array)
  return nil,  nil if start_idx == nil
  end_idx = last_non_negative(start_idx,array)
  return start_idx, end_idx
end

def first_non_negative(idx, array)
  while( array[idx] == -1) && (idx < array.length) do
    idx += 1
  end
  (idx < array.length) ? idx : nil
end

def last_non_negative(idx,array)
  while( array[idx] != -1) && (idx < array.length) do
    idx += 1
  end
  idx-1
end

# reverses an array slice in place, starting 
# at the 'from' index to the 'to' index inclusive.
def reverse(array, from, to)
  while (from < to) do
    array[from],array[to] = array[to], array[from]
    from += 1
    to -= 1
  end
  array
end
