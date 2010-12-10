CluePlayer = {}

function CluePlayer:new(people, weapons, locations)
    local o = {}
    
    o.people = people
    o.weapons = weapons
    o.locations = locations
    
    setmetatable(o, self)
    self.__index = self
    return o
end

function CluePlayer:solve(game)
    local person, weapon, location
    
    while(true) do
        person = self.people[0]
        weapon = self.weapons[0]
        location = self.locations[0]
        
        print("Guessing " .. person .. " with the " .. weapon .. " at the " .. location)
    
        local incorrectGuess = game.isIt(person, weapon, location)
        if (incorrectGuess == nil) then
            break
        else
            print(incorrectGuess .. " is incorrect.")
            if (person == incorrectGuess) then
                table.remove(self.people, 0) 
            elseif (weapon == incorrectGuess) then
                table.remove(self.weapons, 0)
            elseif (location == incorrectGuess) then
                table.remove(self.location, 0)
            end
        end
    end
    
    print (person .. " with the " .. weapon .. " at the " .. location)
end