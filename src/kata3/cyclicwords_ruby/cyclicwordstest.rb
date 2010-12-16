#!/usr/bin/ruby
require "test/unit"
require "cyclicwords"

# tests for cyclic words

class CyclicWordsTest < Test::Unit::TestCase
	def test_emptyarr
		parser = CyclicWords.new()
		assert_equal([], parser.get_cyclic_arrays([]))
	end
	
	def test_oneelementarr
		parser = CyclicWords.new()
		assert_equal(["test"], parser.get_cyclic_arrays(["test"]))
	end

	def test_nocyclicwords
		parser = CyclicWords.new() 		
		assert_equal([["abc"],["def"], ["xyz"]], parser.get_cyclic_arrays(["abc", "def", "xyz"]))
	end	
	
	def test_2cyclicelementsinarr
		parser = CyclicWords.new() 
		assert_equal([["abc", "bca"], ["def"]],parser.get_cyclic_arrays(["abc", "def", "bca"]))
	end
	
	def test_multiplecyclicarrays
		parser = CyclicWords.new()
		assert_equal([["abc", "bca", "cab"], ["def", "efd"], ["xyz"], ["testing"]], 
		            parser.get_cyclic_arrays(["abc", "def", "bca", "xyz", "efd", "testing", "cab"]))
	
	end
	def test_repeatedentries
		parser = CyclicWords.new()
		# input ["abc", "def", "bca", "xyz", "efd", "testing", "bca", "cab", "abc"]
		# code will first remove duplicates from the array by invoking uniq then process array of uniq elements
		assert_equal([["abc", "bca", "cab"], ["def", "efd"], ["xyz"], ["testing"]], 
						parser.get_cyclic_arrays(["abc", "def", "bca", "xyz", "efd", "testing", "bca", "cab", "abc"]))
	
	end

end