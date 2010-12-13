# To change this template, choose Tools | Templates
# and open the template in the editor.

class JavaSource  < String
  attr_accessor :debug

  def sans_comments
    comment = quote = collect_c = nil
    code = self.to_s.split(//)
    code.each_index.collect do |i|
      puts "I=#{i}" if @debug
      #puts code.inspect if @debug
      c = code[i]
      c_plus_one = code[i] + (code[i+1].to_s)
      c_minus_one = code[i] if i == 0
      c_minus_one = code[i-1] + code[i] if i > 0
      puts "c=<#{c}>,c_plus_one=<#{c_plus_one}>, c_minus_one=<#{c_minus_one}>" if @debug
      puts "comment=#{comment}, quote=#{quote}" if @debug
      collect_c = c
      if ! comment and ! quote
        if ! c =~ /[\n*\/'"]/
          nil
        elsif c =~ /[']/
          quote = :single
        elsif code[i] =~ /["]/
          quote = :double
        elsif  c_plus_one == '//'
          comment = :line
          collect_c = nil
        elsif c_plus_one == '/*'
          comment = :multi
          collect_c = nil
        end
      elsif comment == :line and c =~ /[\n]/
        comment = nil
      elsif comment == :multi and c_minus_one == "*/"
        comment = nil
        collect_c = nil
      elsif comment
        collect_c = nil
      elsif quote == :single
        if c_minus_one == %q^\'^
          puts "single quote matched c_minus_one" if @debug
          nil
        elsif c == "'"
          puts "single quote matched c" if @debug
          quote = nil
        end
      elsif quote == :double
        if c_minus_one == %q^\"^
          nil
        elsif quote == :double and c == '"'
          quote = nil
        end
      end
      collect_c
    end.join
  end

  def sans_fluff
    self.to_s.each_line.select { |l| 
      puts "line=(#{l})" if @debug;
      (l =~ /^\s*[\s{}]+\s*$/) == nil  }.join
  end

  def java_stats
    JavaSource.new(self.sans_comments).sans_fluff.split(/\n/).length
  end
  
  def initalize (s)
    @debug = false
    super(s)
  end
end
