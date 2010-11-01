 
# the algorithm for all solutions follows the same model: count how many times
# the ROMAN symbol's value appears in the number, and add the Roman numeral
# representation of that number to the result.
#
# For example, 49 is a combination of 40 ( XL ) + 9 (IX) and therefore
# the corresponding numeral is "XLIX". Likewise, 203 is a combination
# of 100 (C) + 100 + (C) + 1 (I) + 1 (I) + 1 and the corresponding 
# numeral is CCIII

module  Roman

  VALUES = [ [1000, "M"], [900, "CM"], [500, "D"], [400, "CD"], [100, "C"], [90, "XC"],
             [50, "L"], [40, "XL"], [10,"X"], [9, "IX"], [5,"V"], [4, "IV"], [1,"I"] ] 

  # recursive implementation
  def self.convert_recursive(number)
    VALUES.each do |val,symbol|
       if number >= val
         return symbol + convert_recursive(number-val)
       end
    end
    return ""
  end

  # looping implementation
  def self.convert_loop(number)
    sol = ""
    VALUES.each do |val,symbol|
      while number >= val
        sol += symbol
        number -= val
      end
    end
    return sol
  end

  # algebraic implementation
  def self.convert_algebraic(number)
    sol = ""
    VALUES.each do |val,symbol|
      sol += symbol * (number/val)
      number = number % val
    end
    return sol
  end

end
