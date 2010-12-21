package com.demandtec.codingdojo.BooleanAlgebraKata;

import java.util.ArrayList;
import java.util.List;

//A subexpression is an expression that contains no parentheses.
public class SubExpression extends Expression {	
	private List<ExpressionToken> expressionTokens;
	
	private String result = null;
	
	public SubExpression(String expression) {
		super(expression);
		initializeTokens();
	}

	private void initializeTokens() {
		expressionTokens = new ArrayList<ExpressionToken>();
		
		for (int i = 0; i < expression.length(); i++) {
			String token = String.valueOf(expression.charAt(i));
			ExpressionToken expressionToken = new ExpressionToken(token);
			expressionTokens.add(expressionToken);
		}
		
		if (expressionTokens.isEmpty()) {
			throw new IllegalArgumentException("Empty subexpression.");
		}
	}
	
	@Override
	public String evaluate() {
		if (expressionTokens.size() == 1) {
			if (expressionTokens.get(0).isOperand()) {
				return expression;
			} else {
				throw new IllegalArgumentException("Operator " + expression + " does not have any valid operands.");
			}
		}
		
		evaluateNotOperator();
		result = evaluateAndOrOperators().toString();
		
		if (result == null) {
			throw new RuntimeException("Error parsing expression " + expression);
		}
		
		return result;
	}

	private void evaluateNotOperator() {
		List<ExpressionToken> newTokens = new ArrayList<ExpressionToken>();
		int notCount = 0;
		
		for (ExpressionToken token : expressionTokens) {
			if (token.isNot()) {
				notCount++;
				continue;
			} else if (token.isOperand()) {
                token = negate(token, notCount);
                notCount = 0;
            }  else if (token.isOperator() && notCount > 0) {
                throw new IllegalArgumentException("Operand was not found after not operation.");
			} 
			
			newTokens.add(token);
		}
		
		expressionTokens = newTokens;
	}

	private ExpressionToken evaluateAndOrOperators() {
		ExpressionToken operator = ExpressionToken.EMPTY_TOKEN;
		ExpressionToken firstOperand = ExpressionToken.EMPTY_TOKEN;
		ExpressionToken secondOperand = ExpressionToken.EMPTY_TOKEN;
		
		for (ExpressionToken expressionToken : expressionTokens) {
			if (expressionToken.isOperand()) {
				
				if (firstOperand.isEmpty()) {
					firstOperand = expressionToken;
				} else if (secondOperand.isEmpty()){
					secondOperand = expressionToken;
				}
				
			} else if (expressionToken.isOperator()) {
				if (operator.isEmpty()) {
					operator = expressionToken;
				}
			}
			
			if (!operator.isEmpty() && !firstOperand.isEmpty() && !secondOperand.isEmpty()) {
				if (operator.isAnd()) {
				    firstOperand = and(firstOperand, secondOperand);
				} else {
				    firstOperand = or(firstOperand, secondOperand);
				}
				
				secondOperand = ExpressionToken.EMPTY_TOKEN;
				operator = ExpressionToken.EMPTY_TOKEN;
			}
		}
		
		return firstOperand;
	}

	private ExpressionToken negate(ExpressionToken operand, int notCount) {
		if ((notCount % 2) == 0) {
			return operand;
		}
		
		if (operand.isTrue()) {
			return ExpressionToken.FALSE_TOKEN;
		}
		
		return ExpressionToken.TRUE_TOKEN;
	}
	
	private ExpressionToken and(ExpressionToken firstOperand, ExpressionToken secondOperand) {
		if (!firstOperand.isTrue() || !secondOperand.isTrue()) {
			return ExpressionToken.FALSE_TOKEN;
		}
		
		return ExpressionToken.TRUE_TOKEN;
	}

	private ExpressionToken or(ExpressionToken currentOperand1, ExpressionToken currentOperand2) {
		if (currentOperand1.isTrue() || currentOperand2.isTrue()) {
			return ExpressionToken.TRUE_TOKEN;
		} 
		
		return ExpressionToken.FALSE_TOKEN;
	}
}
