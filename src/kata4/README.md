Boolean Algebra Kata
--------------------------

Write a boolean algebra expression evaluator that takes a boolean expression in the form of a string and returns 0 or 1. The allowed values are 0 and 1, and the operators are * for and, + for or, and ! for not. Parentheses may be used for grouping.

Some examples (or test cases):

  * eval("0") returns 0
  * eval("!0") returns 1
  * eval("0 + 1") returns 1
  * eval("0 * 1 * 1") returns 0
  * eval( "!0 * 0 + 1") returns 1 or 0 depending on whether you choose to implement it right or left associative
  * eval(" (0+1) * (1+0)") returns 1 