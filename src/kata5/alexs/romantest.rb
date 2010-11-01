require 'test/unit'
require 'roman'

class RomanNumeralTest < Test::Unit::TestCase

  def test_numerals
    assert_equal("I", Roman.convert_loop(1))
    assert_equal("II", Roman.convert_loop(2))
    assert_equal("III", Roman.convert_loop(3))
    assert_equal("IV", Roman.convert_loop(4))
    assert_equal("V", Roman.convert_loop(5))
    assert_equal("VI", Roman.convert_loop(6))
    assert_equal("VII", Roman.convert_loop(7))
    assert_equal("VIII", Roman.convert_loop(8))
    assert_equal("IX", Roman.convert_loop(9))
    assert_equal("X", Roman.convert_loop(10))
    assert_equal("XXVI", Roman.convert_loop(26))
    assert_equal("XLIX", Roman.convert_loop(49))
    assert_equal("CXXXIX", Roman.convert_loop(139))
    assert_equal("DCLIV", Roman.convert_loop(654))
    assert_equal("CMXCIX", Roman.convert_loop(999))
    assert_equal("MMX", Roman.convert_loop(2010))
  end

  def test_recursive
    assert_equal("I", Roman.convert_recursive(1))
    assert_equal("II", Roman.convert_recursive(2))
    assert_equal("III", Roman.convert_recursive(3))
    assert_equal("IV", Roman.convert_recursive(4))
    assert_equal("V", Roman.convert_recursive(5))
    assert_equal("VI", Roman.convert_recursive(6))
    assert_equal("VII", Roman.convert_recursive(7))
    assert_equal("VIII", Roman.convert_recursive(8))
    assert_equal("IX", Roman.convert_recursive(9))
    assert_equal("X", Roman.convert_recursive(10))
    assert_equal("XXVI", Roman.convert_recursive(26))
    assert_equal("XLIX", Roman.convert_recursive(49))
    assert_equal("CXXXIX", Roman.convert_recursive(139))
    assert_equal("DCLIV", Roman.convert_recursive(654))
    assert_equal("CMXCIX", Roman.convert_recursive(999))
    assert_equal("MMX", Roman.convert_recursive(2010))
  end

  def test_algebraic
    assert_equal("I", Roman.convert_algebraic(1))
    assert_equal("II", Roman.convert_algebraic(2))
    assert_equal("III", Roman.convert_algebraic(3))
    assert_equal("IV", Roman.convert_algebraic(4))
    assert_equal("V", Roman.convert_algebraic(5))
    assert_equal("VI", Roman.convert_algebraic(6))
    assert_equal("VII", Roman.convert_algebraic(7))
    assert_equal("VIII", Roman.convert_algebraic(8))
    assert_equal("IX", Roman.convert_algebraic(9))
    assert_equal("X", Roman.convert_algebraic(10))
    assert_equal("XXVI", Roman.convert_algebraic(26))
    assert_equal("XLIX", Roman.convert_algebraic(49))
    assert_equal("CXXXIX", Roman.convert_algebraic(139))
    assert_equal("DCLIV", Roman.convert_algebraic(654))
    assert_equal("CMXCIX", Roman.convert_algebraic(999))
    assert_equal("MMX", Roman.convert_algebraic(2010))
  end

end
