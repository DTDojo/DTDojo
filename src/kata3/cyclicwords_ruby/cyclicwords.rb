# Cyclic Words program

class CyclicWords 
	
	def get_cyclic_arrays(inputarr)
		if (!inputarr.empty? && inputarr.length > 1)
			# return the result of processing of a unique array (array of non-repeated elements)
			collect_output(inputarr.uniq)
		else
			# return the input
			inputarr
		end
	
	end
	
	def collect_output(input)
		retarr = []
		while !input.empty? do
			temparr = [] #prepare temparray 
			# remove this element from the array			
			current = input.slice!(0) # remove the first element of the array 
			temparr << String.new(current) # make a copy of the first elemnt and put it into temparr	
			if (current.length > 1  && input.length > 1)			
				(temparr << get_rolling_words(input, current)).flatten!	# adding the result array to the temp array and flatten the subarray		
			end
			retarr << temparr # adding the resulting sub-array to the return array
		end
		retarr
	end
	
	
	def get_rolling_words(arr, currentword)
		ret = []
		i = 0;
		while i < currentword.length - 1 do 
			if arr.delete(currentword << currentword.slice!(0,1)) != nil  # this deletes elements of the input array that are the same as modified current word
				ret << String.new(currentword) # adds the copy of the modified current word to the return sub-array if there were elements equal to the modified current word
			end
			i = i + 1 # incrementz the index so we won't be in the infinite loop
		end	
	    ret
	end
end