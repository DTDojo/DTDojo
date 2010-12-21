require 'yaml'
$word_map = YAML::load (File.read 'map.txt')
dict_array = (File.read 'C:\Documents and Settings\twarkent\Desktop\programs\wordgame_kata\real_dictionary.txt').split("\n")

final_array = []
def connector_finder(word1,word2,final_word2,final_array)
	final_array = [word1] if final_array == []
	word1_array = [word1]
	word2_array = [word2]
	word1_count = 0
	word2_count = 0
	if $word_map[word1] != nil && $word_map[word2] != nil
	
		if $word_map[word1].include?(final_word2)
			final_array.push final_word2
			return final_array.join(" -> ")
		end
		
		error_count = 0
		while (word1_array & word2_array) == []
			word1_array.length.times do |x|
				word1_array.push $word_map[(word1_array[x])] if $word_map[(word1_array[x])] != nil
			end
			
			word2_array.length.times do |y|
				word2_array.push $word_map[(word2_array[y])] if $word_map[(word2_array[y])] != nil
			end
			
			word1_array.flatten!
			word2_array.flatten!
			word1_array.uniq!
			word2_array.uniq!
			word1_array = word1_array - [word1]
			word2_array = word2_array - [word2]
			error_count += 1
			
			if word1_array.length == word1_count 
				puts word1_array.to_s
				return "Nope, this one ain't solvable (one of your words is a dead end -- its whole tree group is listed above)"
			elsif word2_array.length == word2_count
				puts word2_array.to_s
				return "Nope, this one ain't solvable (one of your words is a dead end -- its whole tree group is listed above)"
			end
			word1_count = word1_array.length
			word2_count = word2_array.length
			
			if error_count > 100
				puts word2_array.length
				return "This is probably not solvable, but I'm too lazy to check for real =)" 
			end
		end
		
		intersection_array = (word1_array & word2_array)
		
		one_away = 0
		intersection_array.each do |x|
			if $word_map[x].include?(word1)
				final_array.push x
				return connector_finder(x,final_word2,final_word2,final_array)
				one_away += 1
			end
		end
		
		if one_away == 0
			return connector_finder(word1,intersection_array[0],final_word2,final_array)
		end
	elsif $word_map[word1] == nil
		return "Oops, there's no path to your word, friend. ('#{word1}' has no connected words at all!)"
	elsif $word_map[word2] == nil
		return "Oops, there's no path to your word, friend. ('#{word2}' has no connected words at all!)"
	end
end


entry = ''
while entry == ''
	rand1 = dict_array[rand(dict_array.length - 1)]
	rand2 = dict_array[rand(dict_array.length - 1)]
	if rand1.length == rand2.length
		puts "OK, #{rand1} and #{rand2}..."
		puts connector_finder(rand1,rand2,rand2,final_array)
		puts
		puts "Again? (Press Enter to do another, or any other entry to exit)"
		entry = gets.chomp
	end
end
