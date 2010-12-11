ClueGame = {}

function ClueGame:new(people, weapons, locations)
    local o = {}
    
    o:initializeGame(people, weapons, locations)
    
    setmetatable(o, self)
    self.__index = self
    return o
end

function ClueGame:initializeGame(people, weapons, locations)
    self.murderer = getRandomElement(people)
    self.murderWeapon = getRandomElement(weapons)
    self.murderLocation = getRandomElement(locations)
end

function ClueGame:isIt(person, weapon, location)    
    local incorrectValues = {}
    
    if (person ~= self.murderer) then
        table.insert(incorrectValues, person)
    end
    
    if (weapon ~= self.murderWeapon) then
        table.insert(incorrectValues, weapon)
    end
    
    if (location ~= self.murderLocation) then
        table.insert(incorrectValues, location)
    end
    
    if (table.getn(incorrectValues) == 0) then
        return nil
    else
        return getRandomElement(incorrectValues)
    end    
end

function getRandomElement(table)
    local size = table.getn(table)
    return table[math.random(size)]
end