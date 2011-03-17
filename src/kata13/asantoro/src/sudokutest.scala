import org.scalatest.junit.JUnitSuite
import org.scalatest.junit.ShouldMatchersForJUnit
import org.junit.Assert._
import org.junit.Test

class SquareTest extends JUnitSuite with ShouldMatchersForJUnit {

  @Test def verifyOneIsSolved() = {
    val square = new Square()
    square.setValues( List(1) )
    square.solved() should equal (true)
  }

  @Test def verifyTwoOrMoreElementsAreNotSolved() = {
    val square = new Square()
    square.setValues( List(1,2,3) )
    square.solved() should equal (false)
  }

}

class GroupTest extends JUnitSuite with ShouldMatchersForJUnit {

  @Test def verifyAddedCorrectly() = {
    val group = new Group()
    val square1 = new Square()
    val square2 = new Square()
    group.add(square1)
    group.add(square2)
    assertSame( square1, group.groupSquares(0) )
    assertSame( square2, group.groupSquares(1) )
 } 

}
