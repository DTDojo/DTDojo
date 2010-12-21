--Word Dictionary class
WordDictionary = {}

function WordDictionary:new()
	local o = {}
	setmetatable(o, self)
	self.__index = self
	self.iters = 0
	
	self:initWords()
	
	return o
end

function WordDictionary:initWords()
	self.fourletterwords = self:loadWordList("../fourletterwords.txt")
	self.fiveletterwords = self:loadWordList("../fiveletterwords.txt")
end

function WordDictionary:loadWordList(filename)
	local file = io.lines(filename)
	local dictionary = {}
	
	for line in file do
		table.insert(dictionary, line)
	end
	
	return dictionary
end

function WordDictionary:findCloseMatches(node)
	local closeMatches = {}
	local word = node.value
	
	if (#word == 4) then
		closeMatches = self:_findCloseMatches(node, self.fourletterwords)
	elseif (#word == 5) then
		closeMatches = self:_findCloseMatches(node, self.fiveletterwords)
	else
		error("No dictionary for word length of " .. wordLength)
	end
	
	return closeMatches
end

function WordDictionary:_findCloseMatches(node, dictionary)
	local closeMatches = {}
	local word = node.value
	
	for k,v in ipairs(dictionary) do		
		if (getHammingDistance(v, word) == 1) then
			childNode = Node:new(node, v)
			table.insert(closeMatches, childNode)
			table.remove(dictionary, k)
		end
	end
	
	print(table.getn(dictionary))
	
	return closeMatches
end

function getHammingDistance(word1, word2)
	if (#word1 ~= #word2) then
		return nil
	end
	
	local distance = 0
	
	for i = 1, #word1 do
		if (word1:sub(i,i) ~= word2:sub(i,i)) then
			distance = distance + 1
		end
	end
	
	return distance
end

--Simple node class
Node = {}

function Node:new(parent, value)
	local o = {}
	setmetatable(o, self)
	self.__index = self
	
	o.parent = parent
	o.value = value
	return o
end