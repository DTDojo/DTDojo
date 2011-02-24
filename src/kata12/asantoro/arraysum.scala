/**
 * Returns an array of integers such that
 *    + the sum of the elements in the array is equal to the target sum value
 *    + each element in the array corresponds to one element in the data array
 */
def findSum(sum:Int, data:List[Int]) : List[Int]  = {
  data.foreach( s => 
    if (sum == s) {
      return List(s) 
    } else {
      val restSum = findSum(sum-s, data-s)
      if (restSum != List()) {
	return s :: restSum
      }
    }
  )
  List()
}


//------------------------------------------------------------------
// TEST PART
//------------------------------------------------------------------

def testEqual(val1:Any, val2:Any) = {
  if (val1 == val2) {
    println(".")
  } else {
    println("E: expected " + val1 + " but was " + val2)
  }
}

// tests all single element use cases
testEqual(List(1), findSum(1, List(1,2,6) ) )
testEqual(List(2), findSum(2, List(1,2,6) ) )
testEqual(List(6), findSum(6, List(1,2,6) ) )

// tests two elements use cases
testEqual(List(1,2), findSum(3, List(1,2,6) ) )
testEqual(List(1,6), findSum(7, List(1,2,6) ) )
testEqual(List(2,6), findSum(8, List(1,2,6) ) )

// test three elements use case
testEqual( List(1,2,6), findSum(9, List(1,2,6) ) )

// test impossible sums
testEqual( Nil, findSum(-10, List(1,2,6) ) )
testEqual( Nil, findSum(10, List(1,2,6) ) )
testEqual( Nil, findSum(4, List(1,2,6) ) )
