#!/usr/bin/ruby
require "test/unit"
require "romannumerals"
# for testing of RomanNumeralsR uncomment the following line and comment out preceeding line
# require "romannumeralsr"

class RomanNumeralsTest < Test::Unit::TestCase

	def set_up()
		# for testing of RomanNumeralsR uncomment the following line and comment out the next line of set_up method
		# return RomanNumeralsR.new()
		return RomanNumerals.new()
	end
	def test_to_roman_basic_1
		numerals = set_up()
		assert_equal("I", numerals.to_roman(1))		
	end
	
	def test_to_roman_addition_to1
		numerals = set_up()
		assert_equal("III", numerals.to_roman(3))
	end
			
	def test_to_roman_addition_to5
		numerals = set_up()
		assert_equal("VI", numerals.to_roman(6))
	end

	def test_to_roman_subtraction_from10
		numerals = set_up()
		assert_equal("IX", numerals.to_roman(9))
	end	
	
	def test_to_roman_41
		numerals = set_up()
		assert_equal("XLI", numerals.to_roman(41))
	end
	def test_to_roman_addition_subtraction_complex
		numerals = set_up()
		assert_equal("CCXCI", numerals.to_roman(291))
	end
	
	def test_to_roman_next_year
		numerals = set_up()
		assert_equal("MMXI", numerals.to_roman(2011))
	end
	def test_to_roman_1999
		numerals = set_up()
		assert_equal("MCMXCIX", numerals.to_roman(1999))
	end
	def test_to_roman_max_number
		numerals = set_up()
		assert_equal("MMMCMXCIX", numerals.to_roman(3999))
	end
	
	def test_smallest_pandigital_num () 
		numerals = set_up()
		assert_equal("MCDXLIV", numerals.to_roman(1444))
	end 
	
	def test_longest_output() 
		numerals = set_up()
		assert_equal("MMMDCCCLXXXVIII", numerals.to_roman(3888))
	end 
	
	def test_to_roman_bigger_than_max
		numerals = set_up()
		assert_equal(nil, numerals.to_roman(4999))
	end
	
	


end