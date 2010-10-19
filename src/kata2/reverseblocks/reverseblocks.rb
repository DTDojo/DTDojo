# my first ruby program!
class ReverseBlocks

	def reverseArrayBlocks(arr) 
	    # if array is empty, or its length is less than 2 elements or it does not include -1
		# then there is no point in reversing 
	    resultarr = arr
		if (!arr.empty? && (arr.length > 2) && (arr.include?(-1))) 
		    # redefine
			resultarr = []
			#only if array is not empty and 
			idx = 0;
			while (idx = arr.index(-1))!= nil do
				# remove a slice from the original array and 
				# push the removed slice into the result array	
				if (idx == 0) 
					#it is the first element
					resultarr << arr.slice!(idx)
				else
					# it is a block
					resultarr << arr.slice!(0..(idx-1))
				end
			end
			# need to push the remaining part of the original array 
			# to the result array if original array is not empty
			if (!arr.empty?) 
				resultarr << arr
			end			
			# reverse this array -- this will reverse the blocks			
			resultarr.reverse!
						
			# flatten this array
			resultarr.flatten!
			
		end
		resultarr
	end 

end
