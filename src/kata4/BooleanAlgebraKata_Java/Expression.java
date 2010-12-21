package com.demandtec.codingdojo.BooleanAlgebraKata;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Expression {

	protected String expression;
	
	public Expression(String expression) {
		if (!areParenthesesBalanced(expression)) {
			throw new IllegalArgumentException("Parentheses are not balanced.");
		}
		
		this.expression = removeWhiteSpace(expression); 
	}

	public String evaluate() {
		if (expression.indexOf('(') >= 0) {
			evaluateSubExpressions();
		} 
		
		SubExpression subExpression = new SubExpression(expression);
		return subExpression.evaluate();
	}

	List<BeginEndIndexPair> indexPairs = new ArrayList<BeginEndIndexPair>();
	private void evaluateSubExpressions() {
		
		generateParenthesesIndexPairs(indexPairs);
		updateExpression(indexPairs);
	}

	private void generateParenthesesIndexPairs(List<BeginEndIndexPair> indexPairs) {
		Stack<Integer> leftParenthesisIndices = new Stack<Integer>();
		
		for (int i = 0; i < expression.length(); i++) {
			char character = expression.charAt(i);
			
			if (character == '(') {
				leftParenthesisIndices.push(i);
			} else if (character == ')') {
				if (leftParenthesisIndices.isEmpty()) {
					throw new IllegalArgumentException("Parentheses are not balanced.");
				}
				
				Integer beginIndex = leftParenthesisIndices.pop();
				Integer endIndex = i;
				
				BeginEndIndexPair indexPair = new BeginEndIndexPair(beginIndex, endIndex);
				indexPairs.add(indexPair);
			}
		}
	}

	private void updateExpression(List<BeginEndIndexPair> indexPairs) {
		for (BeginEndIndexPair indexPair : indexPairs) {
			Integer beginIndex = indexPair.getBeginIndex();
			Integer endIndex = indexPair.getEndIndex();
			
			String result = evaluateSubExpression(beginIndex, endIndex);
			replaceSubExpression(result, beginIndex, endIndex);
		}
	}

	private String evaluateSubExpression(Integer beginIndex, Integer endIndex) {
		String subString = expression.substring(beginIndex + 1, endIndex);
		SubExpression subExpression = new SubExpression(subString);
		String result = subExpression.evaluate();
		return result;
	}
	
	private void replaceSubExpression(String result, Integer beginIndex,
			Integer endIndex) {
		result = padRight(result, endIndex - beginIndex + 1);
		expression = expression.substring(0, beginIndex) + result + expression.substring(endIndex + 1);
	}

	
	
	private boolean areParenthesesBalanced(String expression) {
		int leftParentheses = 0;
		int rightParentheses = 0;
		
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == '(') {
				leftParentheses++;
			}
			
			if (expression.charAt(i) == ')') {
				rightParentheses++;
			}
		}
		
		return leftParentheses == rightParentheses;
	}
	
	private String removeWhiteSpace(String string) {
		return string.replaceAll(" ", "");
	}
	
	private String padRight(String string, int numberToPad) {
	     return String.format("%1$-" + numberToPad + "s", string);  
	}


}
