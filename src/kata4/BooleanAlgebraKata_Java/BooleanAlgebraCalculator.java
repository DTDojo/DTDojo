package com.demandtec.codingdojo.BooleanAlgebraKata;

public class BooleanAlgebraCalculator {

	public static String eval(String string) {
		Expression expression = new Expression(string);
		return expression.evaluate();
	}

}
