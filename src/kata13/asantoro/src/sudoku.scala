
/*
 * The Square class represents one square in the
 * sudoku grid
 */
class Square {

  var values = List(1,2,3,4,5,6,7,8,9)

  def solved() = {
    (values.length == 1)
  }

  def setValues(newValues : List[Int] ) = {
    values = newValues
  }

  def getValues() = {
    values
  }

}
