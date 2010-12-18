# To change this template, choose Tools | Templates
# and open the template in the editor.

$:.unshift File.join(File.dirname(__FILE__),'..','lib')

require 'test/unit'
require 'java_source'

class JavaSourceTest < Test::Unit::TestCase
  
  SAMPLE1 = 'test/sample1.java'
  SAMPLE2 = 'test/sample2.java'

  S_COMMENT1 = 'a // comment string'
  S_COMMENT2 = 'a // comment "string"'
  S_COMMENT3 = 'a / / comment string'
  S_COMMENT4 = 'a "// comment string"'
  S_COMMENT5 = "a // comment string\nb // a second comment"
  S_COMMENT6 = "a '// comment string\n'b // a second comment"
  S_COMMENT7 = "a '// comment string'"

  M_COMMENT1 = 'a /* comment string */ b'
  M_COMMENT2 = 'a /* comment "string" */ b'
  M_COMMENT3 = 'a / * comment string b'
  M_COMMENT4 = 'a "/* comment string */" b'
  M_COMMENT5 = "a /* comment string\n*/ b /* a second comment */ c"
  M_COMMENT6 = "a '/* comment string\n */'b /* a second comment*/ c"
  M_COMMENT7 = "a '/* comment string'*/ b"

  SAMPLE1_SE = 'test/sample1_sans_exposition.java'
  SAMPLE2_SE = 'test/sample2_sans_exposition.java'
  def test_file_sans_exposition
    
    contents = File.open(SAMPLE1,'r') {|f| f.read}
    contents_se = File.open(SAMPLE1_SE,'r') {|f| f.read}
    assert_equal(contents_se, JavaSource.new(contents).sans_comments,'file1 w/o comments')

    contents = File.open(SAMPLE2,'r') {|f| f.read}
    contents_se = File.open(SAMPLE2_SE,'r') {|f| f.read}
    assert_equal(contents_se, JavaSource.new(contents).sans_comments,'file2 w/o comments')
  end
  
  def test_multi_comment
    assert_equal('a  b',JavaSource.new(M_COMMENT1).sans_comments,'ml simple')
    assert_equal('a  b',JavaSource.new(M_COMMENT2).sans_comments, 'ml simple hidden quote')
    assert_equal(M_COMMENT3,JavaSource.new(M_COMMENT3).sans_comments,'ml simple broken quote')
    assert_equal("a  b  c",JavaSource.new(M_COMMENT5).sans_comments,'ml simple multi line')
    js = JavaSource.new(M_COMMENT6)
    assert_equal("a '/* comment string\n */'b  c",js.sans_comments,'ml simple single quote single line')
    assert_equal(M_COMMENT7,JavaSource.new(M_COMMENT7).sans_comments,'ml simple double quoted comment')
  end

  def test_to_s
    contents = File.open(SAMPLE1,'r') {|f| f.read}
    assert_equal(contents, JavaSource.new(contents).to_s)

    contents = File.open(SAMPLE2,'r') {|f| f.read}
    assert_equal(contents, JavaSource.new(contents).to_s)
  end

  def test_single_comment
    js = JavaSource.new(S_COMMENT1)
    assert_equal('a ',js.sans_comments,'sc simple')    
    assert_equal('a ',JavaSource.new(S_COMMENT2).sans_comments,'sc simple hidden quote')
    assert_equal(S_COMMENT3,JavaSource.new(S_COMMENT3).sans_comments,'sc simple broken quote')
    assert_equal(S_COMMENT4,JavaSource.new(S_COMMENT4).sans_comments,'sc simple double quoted comment')
    assert_equal("a \nb ",JavaSource.new(S_COMMENT5).sans_comments,'sc simple multi line')    
    js = JavaSource.new(S_COMMENT6)
    assert_equal("a '// comment string\n'b ",js.sans_comments,'sc simple single quote single line')
    assert_equal(S_COMMENT7,JavaSource.new(S_COMMENT7).sans_comments,'sc simple double quoted comment')
  end

  def test_sans_fluff_string
    assert_equal('',JavaSource.new("\n\n\n").sans_fluff,'empty file')
    assert_equal("one line\n",JavaSource.new("\none line\n\n").sans_fluff,'one line')
    assert_equal("",JavaSource.new("\n { \n }\n { } \n   ").sans_fluff,'nothing here')
    assert_equal("just_start_a_block {\n",JavaSource.new("just_start_a_block {\n}").sans_fluff,'block start')
    assert_equal("} end_a_block",JavaSource.new("{ \n} end_a_block").sans_fluff,'block end')
    # technically this is code, and we should keep it, but does not meet the test criteria of lines w only { go..
    assert_equal("a quote'\n'\n",JavaSource.new("a quote'\n{}\n'\n").sans_fluff,'block end')
  end

  SAMPLE1_SF = 'test/sample1_sans_fluff.java'
  SAMPLE2_SF = 'test/sample2_sans_fluff.java'

  def test_sans_fluff_file
    contents = File.open(SAMPLE1,'r') {|f| f.read}
    contents_sf = File.open(SAMPLE1_SF,'r') {|f| f.read}
    assert_equal(contents_sf, JavaSource.new(contents).sans_fluff,'file1 w/o fluff')

    contents = File.open(SAMPLE2,'r') {|f| f.read}
    contents_sf = File.open(SAMPLE2_SF,'r') {|f| f.read}
    assert_equal(contents_sf, JavaSource.new(contents).sans_fluff,'file2 w/o fluff')
  end

  def test_java_stats
    contents = File.open(SAMPLE1,'r') {|f| f.read}
    assert_equal(2, JavaSource.new(contents).java_stats,'file1 stats')

    contents = File.open(SAMPLE2,'r') {|f| f.read}
    assert_equal(3, JavaSource.new(contents).java_stats,'file2 stats')
  end
end