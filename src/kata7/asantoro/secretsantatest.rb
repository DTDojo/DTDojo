require 'test/unit'

require 'secretsanta'

class SecretSantaTest < Test::Unit::TestCase

  def test_empty
    result = secret_santa( [ ] )
    assert_equal(0, result.size)
  end

  def test_two_people
    result = secret_santa( [ 'mickey mouse', 'donald duck' ] )
    assert_equal(2, result.size)
    assert_equal('mickey mouse', result['donald duck'])
    assert_equal('donald duck', result['mickey mouse'])
  end

  def test_two_families
    result = secret_santa( ['mickey mouse', 'donald duck', 'daisy duck', 'minnie mouse'] )
    assert_equal(4, result.size)
    result.each_pair do |person, santa|
      assert( ! related(person, santa) )
    end
  end

  def test_big_list
    big_list = create_big_list()
    result = secret_santa( big_list )
    result.each_pair do |person, santa|
      assert( !related(person, santa) )
    end
  end

  def test_infeasible_lists
    assert_raise RuntimeError do
      secret_santa( ['mickey mouse'] )
    end
    assert_raise RuntimeError do
      secret_santa( ['mickey mouse', 'minnie mouse', 'donald duck'] )
    end
  end

  def create_big_list
    big_list = [ ]
    first_names = [ "Alpha", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf" ]
    last_names =  [ "Brown", "Gray", "Black", "White", "Silver" ]
    first_names.each do |first_name|
      last_names.each do |last_name|
        big_list << "#{first_name} #{last_name}"
      end
    end
    return big_list.shuffle
  end

end
