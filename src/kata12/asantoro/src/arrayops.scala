object ArrayOps {

  def findSum(sum:Int, data:List[Int]) : List[Int] = {
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

}
