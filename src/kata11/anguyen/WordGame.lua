require "WordDictionary"

WordGame = {}

function WordGame:new()
	local o = {}
	setmetatable(o, self)
	self.__index = self
	
	return o
end

function WordGame:findMatch(source, destination)
	self.dictionary = WordDictionary:new()
	local endNode = self:_findMatch(source, destination)
	if (endNode == nil) then
		print("No path found.")
	else
		self:printPath(endNode)
	end
end

function WordGame:_findMatch(source, destination)
	local sourceNode = Node:new(nil, source)
	local queue = {sourceNode}
	
	while (table.getn(queue) > 0) do
		local current = table.remove(queue, 1)
		
		if (current.value == destination) then
			return current
		end
		
		closeMatches = self.dictionary:findCloseMatches(current)
		for i,childNode in ipairs(closeMatches) do
			if (childNode.value ~= source) then
				table.insert(queue, childNode)
			end
		end
	end
	
	return nil
end

function WordGame:printPath(node)
	local currentNode = node
	local path = {currentNode.value}
	
	while (currentNode.parent ~= nil) do
		currentNode = currentNode.parent
		table.insert(path, 1, currentNode.value)
	end
	
	print(table.concat(path, "->"))
end