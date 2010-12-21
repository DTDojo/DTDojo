# BlockReverseKata solution.

def block_reverse( array ) 
  array_of_blocks = split_blocks(array)
  array_of_blocks.reverse.flatten
end

# splits the array into an array of arrays,
# where each subarray is either [-1] or a block
def split_blocks(array)
  array.inject( [ [] ] )  do |array_of_blocks, val|
    if val == -1
      array_of_blocks << [-1]
      array_of_blocks << []  
    else 
      array_of_blocks[-1] << val
    end
    array_of_blocks
  end
end

