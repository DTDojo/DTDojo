puts 'Hey buddy, wanna get some roman numerals? Gimme a positive number (no longer than 14 digits, please).'
def chomper
	while true
		orig_number = gets.chomp
		if (orig_number.to_i <= 99999999999999) && (orig_number.to_i <= 0) && orig_number != 'exit'
			puts 'Hey! That ain\'t within the range I said! Try again'
		else
			return orig_number
		end
	end
end

def roman working_number
roman_array = [] #make an array to hold our roman number
array1 = [1,10,100,1000,10000,100000,1000000,10000000] #push values out to an array1 -- 1, 10, 100, 1000
array2 = [5,50,500,5000,50000,500000] #push half values out to an array2 -- 5, 50, 500
arraya = ['I','X','C','M','X','C','M','']
arrayb = ['V','L','D','V','L','D']

	while true #figure out length of string = stringlength
		stringlength = working_number.to_s.length
		if (working_number >= 5000) && (roman_array[0] != '(')
			roman_array.push '('
			was_over_5000 = true
		end
		if (working_number.to_i < 4000000) && (working_number >= (array1[stringlength.to_i] / 2)) #if working number is greater than half of the total allowed for its place
			if (working_number / array1[stringlength.to_i - 1]) == 9 #if it's an IX case
				roman_array.push (arraya[stringlength.to_i - 1].to_s + arraya[stringlength.to_i].to_s) #push the ix and subtract 9 times whatever the character should be
				working_number = (working_number - (array1[stringlength.to_i - 1] * 9))
			else
				roman_array.push (arrayb[stringlength.to_i - 1]) #push its corresponding halfway-number 
				working_number = (working_number - (array1[stringlength.to_i] / 2)) #and subtract that halfway-number from our working total
			end
		elsif working_number.to_i >= 4000000
			puts working_number
			bignum_subtract = working_number / 1000000
			roman_array.push ('M' * (bignum_subtract))
			working_number = (working_number % (bignum_subtract * 1000000))
		else #if it's not more than half
			if (working_number / array1[stringlength.to_i - 1]) == 4 #if it's an IV case
				roman_array.push (arraya[stringlength.to_i - 1].to_s + arrayb[stringlength.to_i - 1].to_s)#push the iv and subtract 4 times whatever the character should be
				working_number = (working_number - (array1[stringlength.to_i - 1] * 4))
			else
				roman_array.push (arraya[stringlength.to_i - 1] * (working_number / array1[stringlength.to_i - 1]))
				working_number = (working_number % array1[stringlength.to_i - 1])
			end
		end
		if (working_number < 5000) && (was_over_5000 == true)
			roman_array.push ')'
			was_over_5000 = false
		end
		if working_number == 0
			return roman_array.join('')
		end
	end
end
working_number = ''
while true
	if working_number != ''
		puts 'Got another one?  Type it now, or \'exit\' to quit.'
	end
	orig_number = chomper
	if orig_number == 'exit'
		puts
		puts 'OK!  I\'m outta here!'
		break
	end
	working_number = orig_number.to_i	
	roman_number = (roman working_number)
	puts
	puts 'What a piece of cake!  The roman numeral form of ' + orig_number.to_s + ' is ' + roman_number.to_s + '!'
end





