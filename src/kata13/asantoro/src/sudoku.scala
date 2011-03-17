
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


class Group {

  var groupSquares = new Array[Square](9)
  var index = 0

  def add(square:Square) = {
    groupSquares(index) = square
    index += 1
  }

}


