# roman numerals recursive program

class RomanNumeralsR
	@@roman_map={1000=>"M", 500=>"D", 100=>"C", 50=>"L", 10=>"X", 5=>"V", 1=>"I" } # maps all known keys to char without bars
	@@subtraction_arr=[[1000, 100], [500, 100], [100,10], [50, 10], [10, 1], [5,1], [1, 0]]
	@@MAX_ROMAN=3999
	
	def to_roman(num) 
		if (num > @@MAX_ROMAN) 
			print "Cannot convert number ", num , " as it's bigger than ", @@MAX_ROMAN, " - cannot output dash on top of the char"
			return nil
		else 
			if (@@roman_map.has_key?(num))
				return @@roman_map[num] 
			else 
				# iterate through the @@subtraction_arr
				@@subtraction_arr.each do |leftKey, rightKey|
					if (num > leftKey) 
						# addition principle (recursion)
						return @@roman_map[leftKey] + to_roman(num - leftKey) #left key always exists in a map
					else 
						if ((num < leftKey) && ((num + rightKey) >= leftKey ))
							# subtraction principle (recursion)
							return (rightKey > 0 ? @@roman_map[rightKey] : "")  + to_roman(num + rightKey)			
						end				
					end	
				end
			end
		end		
	end
end