package com.demandtec.codingdojo.BooleanAlgebraKata;

public class ExpressionToken {	
	public static final ExpressionToken EMPTY_TOKEN = new ExpressionToken();
	public static final ExpressionToken TRUE_TOKEN = new ExpressionToken("1");
	public static final ExpressionToken FALSE_TOKEN = new ExpressionToken("0");
    
    private String token;
	
	public ExpressionToken() {
		this.token = new String();
	}
	
	public ExpressionToken(String token) {
		this.token = String.valueOf(token);
		
		if (!isValid()) {
			throw new IllegalArgumentException(token + " is not a valid operator or operand.");
		}
	}

	public boolean isOperand() {
		return isTrue() || isFalse();
	}
	
	public boolean isOperator() {
		return isOr() || isAnd() || isNot();
	}
	
	public boolean isNot() {
		return token.equals("!");
	}
	
	public boolean isAnd() {
		return token.equals("*");
	}
	
	public boolean isOr() {
		return token.equals("+");
	}
	
	public boolean isTrue() {
		return token.equals("1");
	}
	
	public boolean isFalse() {
		return token.equals("0");
	}
	
	public boolean isEmpty() {
		return token.isEmpty();
	}
	
	@Override
	public String toString() {
		return token;
	}
	
	private boolean isValid() {
		return token != null  && (isOperator() || isOperand() || isEmpty());
	}
}
