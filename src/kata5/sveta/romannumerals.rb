# roman numerals program

class RomanNumerals
	@@roman_map={1000=>"M", 500=>"D", 100=>"C", 50=>"L", 10=>"X", 5=>"V", 1=>"I" }
	@@roman_keys=@@roman_map.keys # in the descending order
	@@MAX_ROMAN=3999
	
	def to_roman(num)
		if (num > @@MAX_ROMAN)
			print "Cannot convert number ", num , " as it's bigger than ", @@MAX_ROMAN, " - cannot output dash on top of the char"
			return nil
		else 
			if (@@roman_map.has_key?(num))
				return @@roman_map[num] 
			else 
				ret=""
				# creating a return string
				@@roman_map.each do |key, value|
					if (num > 0)  # no need to go down the list if number is 0
						count,num=num.divmod(key) # this line returns integer division and remainder values
						# check if the count is greater than 0, otherwise continue with the loop
						if (count > 0)
							# check if the count is 4
							if (count==4)
								# getting the previous key value (keys are in descending order)
								prevkey=@@roman_keys[@@roman_keys.index(key) - 1]
								ret << value << @@roman_map[prevkey] #subtraction principle
							else 
								keystart=key.to_s.slice(0).to_i  # need to know if key starts with 5 
								numstart=(num+key).to_s.slice(0).to_i	# need to know if number before divmod call started with 9					
								if (keystart==5 && numstart==9) 							
									prevkey=@@roman_keys[@@roman_keys.index(key) - 1] # previous key e.g. if current key is 5, previous key is 10
									nextkey=@@roman_keys[@@roman_keys.index(key) + 1] # next key e.g. if current key is 5, next key is 1
									
									ret << @@roman_map[nextkey] << @@roman_map[prevkey] # subtraction principle
									num=(num+key)-(prevkey - nextkey) # need to change the number 
									
								else 																							
									ret << (value * count)

								end
							end 
						end
					end
				end
				return ret
			end
		end
	end

end