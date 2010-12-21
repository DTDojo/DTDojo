package com.demandtec.codingdojo.BooleanAlgebraKata;

import junit.framework.TestCase;


public class BooleanAlgebraCalculatorTest extends TestCase {
	public void testNoOperators() {
		assertEquals("0", BooleanAlgebraCalculator.eval("0"));
	}
	
	public void testSimpleNot() {
		assertEquals("1", BooleanAlgebraCalculator.eval("!0"));
	}
	
	public void testNestedParentheses() {
		assertEquals("1", BooleanAlgebraCalculator.eval("(!((((0)))))"));
	}
	
	public void testSimpleOr() {
		assertEquals("1", BooleanAlgebraCalculator.eval("(0 + 1)"));
	}
	
	public void testMultipleAnds() {
		assertEquals("0", BooleanAlgebraCalculator.eval("0 * 1 * 1"));
	}
	
	public void testAndAndNot() {
		assertEquals("1", BooleanAlgebraCalculator.eval("!0 * 0 + 1"));
	}
	
	public void testComplex() {
	    assertEquals("1", BooleanAlgebraCalculator.eval("(!0 * 0 + 1) * (!!0 * !!0 + !!(!!!!1))"));
	}
	
	public void testSimpleParentheses() {
		assertEquals("1", BooleanAlgebraCalculator.eval("   (0+1) * (1 + 0) "));
	}
	
	public void testMultipleNot() {
		assertEquals("1", BooleanAlgebraCalculator.eval("   !!!(0) * !(0 + 0) "));
	}
	
	public void testUnbalancedParentheses1() {
		try {
			BooleanAlgebraCalculator.eval("( 0 + 1)))((");
		} catch (IllegalArgumentException e) {
			assertEquals("Parentheses are not balanced.", e.getMessage());
			return;
		}
		
		fail();
	}
	
	public void testUnbalancedParentheses2() {
		try {
			BooleanAlgebraCalculator.eval("((!!1)");
		} catch (IllegalArgumentException e) {
			assertEquals("Parentheses are not balanced.", e.getMessage());
			return;
		}
		
		fail();
	}
	
	public void testIncorrectOperands() {
		try {
			BooleanAlgebraCalculator.eval("2");
		} catch (IllegalArgumentException e) {
			assertEquals("2 is not a valid operator or operand.", e.getMessage());
			return;
		}
		
		fail();
	}
	
	public void testEmptyExpression() {
		try {
			BooleanAlgebraCalculator.eval("()()");
		} catch (IllegalArgumentException e) {
			assertEquals("Empty subexpression.", e.getMessage());
			return;
		}
		
		fail();
	}
	
	public void testNoOperands() {
		try {
			BooleanAlgebraCalculator.eval("+");
		} catch (IllegalArgumentException e) {
			assertEquals("Operator + does not have any valid operands.", e.getMessage());
			return;
		}
		
		fail();
	}
	
	public void testIllegalNot() {
        try {
            BooleanAlgebraCalculator.eval("(!!!1 * !!*)");
        } catch (IllegalArgumentException e) {
            assertEquals("Operand was not found after not operation.", e.getMessage());
            return;
        }
        
        fail();
    }
	
}
