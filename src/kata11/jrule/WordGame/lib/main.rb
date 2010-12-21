# To change this template, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')
require 'word_list'
require 'word_game'

wl = WordList.new(nil)
wl.load_file('../../../fiveletterwords.txt')
wl.load_file('../../../fourletterwords.txt')

#puts wg = WordGame.new('tree','leaf',wl.words)
#puts wg = WordGame.new('tree','leaf',wl.words)
puts wg = WordGame.new('slams','slony',wl.words)
puts "Final Final List: #{wg.final_list.inspect}"


