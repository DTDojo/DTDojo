import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Assert._
import org.junit.Test

class ArraySumTestSuite extends JUnitSuite with ShouldMatchersForJUnit {

  val array = List(1,2,6)

  @Test def verifyOneElementSums() = {
    ArrayOps.findSum(1, array) should be ( List(1) )
    ArrayOps.findSum(2, array) should be ( List(2) )
    ArrayOps.findSum(6, array) should be ( List(6) )
  }

  @Test def verifyTwoElementSums() = {
    ArrayOps.findSum(3, array) should be ( List(1,2) )
    ArrayOps.findSum(7, array) should be ( List(1,6) )
    ArrayOps.findSum(8, array) should be ( List(2,6) )
  }

  @Test def verifyThreeElementSums() = {
    ArrayOps.findSum(9, array) should be ( List(1,2,6) )
  }

  @Test def verifyNoSum() = {
    ArrayOps.findSum(-1, array) should be ( List() )
    ArrayOps.findSum(10, array) should be ( List() )
    ArrayOps.findSum( 5, array) should be ( List() )
  }

}

