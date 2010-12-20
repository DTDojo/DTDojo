dict_array = (File.read 'C:\Documents and Settings\twarkent\Desktop\programs\wordgame_kata\real_dictionary.txt').split("\n")

puts "Give me word 1 (4 or 5 characters):"
word1 = gets.chomp

puts  "Give me word 2 (4 or 5 characters):"
word2 = gets.chomp

seen_words = []
final_string = ''
raise "One'o'those ain't a word..." if (dict_array.include?(word1) == false || dict_array.include?(word2) == false)
raise "They have to be the same length, doofus." if word1.length != word2.length

def connected_word_collector(word1,word2,dict_array,seen_words,final_string)
final_string = final_string + word1 + " -> " # make a final string
seen_words.push word1 if !seen_words.include?(word1) # make a seen_words array so we don't make this harder than we need to and create loops
possible_paths = [] #to hold words that we haven't seen before but are 1 letter away from our word1
word1_array = word1.split('')

	dict_array.each do |x|
		if x.length == word1.length #do nothing if the words aren't the same size; we can't add letters
			count = 0
			char_matches = 0
			x.split('').each do |y| #a really clumsy way to figure out if they're 1 letter off...
				if y == word1_array[count]
					char_matches = char_matches + 1
				end
				count = count + 1
			end

			if char_matches == word1.length - 1 && seen_words.include?(x) == false
				possible_paths.push x #make a list of the close words (that we haven't tested yet)
			end

			if possible_paths.include?(word2) #if you've found a solution, put out our final string and exit
				final_string = final_string + word2
				puts final_string
				exit
			end
		end
	end
	possible_paths.each do |z| #otherwise, take each word in our tree and run our clumsy solution on it recursively
		connected_word_collector(z,word2,dict_array,seen_words,final_string)
	end
	nil
end

puts "Sorry, no solution..." if connected_word_collector(word1,word2,dict_array,seen_words,final_string) == nil



