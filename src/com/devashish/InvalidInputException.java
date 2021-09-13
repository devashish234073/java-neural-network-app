package com.devashish;

public class InvalidInputException extends Exception  {
	public InvalidInputException(int expectedNumberofInputs,int actualNumberOfInput) {
		super("Expected "+expectedNumberofInputs+" inputs while got "+actualNumberOfInput+".");
	}
}
