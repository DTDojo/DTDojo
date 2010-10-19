#!/usr/bin/ruby
require "test/unit"
require "reverseblocks"

# tests for ReverseBlocks

class ReverseBlocksTest < Test::Unit::TestCase
	
	# test empty array
	def test_emptyarray
		reverseblocks = ReverseBlocks.new()
		res = reverseblocks.reverseArrayBlocks([])	
		assert(res.empty?, "expected empty return array")
	end
	
	# test array of one element not -1
	def test_oneelementarray
		reverseblocks = ReverseBlocks.new()
		input = [1]
		res = reverseblocks.reverseArrayBlocks(input)
		assert(input.eql?(res), "result array should be the same as parameter passed to a method")
	end
	
	def test_arraywithseparators
		reverseblocks = ReverseBlocks.new()
		input = [-1, -1, 1, 2, 3, -1, 4, 5, -1, -1, -1, 6, 7, 8, -1, 9, -1]
		res = reverseblocks.reverseArrayBlocks(input)
		expected = [-1, 9, -1, 6, 7, 8, -1, -1, -1, 4, 5, -1, 1, 2, 3, -1, -1]
		assert(expected.eql?(res), "result array is not as expeted")
	end
	
	def test_noblockseparator
		reverseblocks = ReverseBlocks.new()
		input = [1, 2, 3, 4, 5, 6, 7]
		res = reverseblocks.reverseArrayBlocks(input)
		expected = [1, 2, 3, 4, 5, 6, 7]
		assert(expected.eql?(res), "expected array with the same element order")
	end
	
	def test_allseparators
		reverseblocks = ReverseBlocks.new()
		input = [-1, -1, -1, -1]
		expected = [-1, -1, -1, -1]
		res = reverseblocks.reverseArrayBlocks(input)
		assert(expected.eql?(res), "expected array with the same element order")
	end

end
