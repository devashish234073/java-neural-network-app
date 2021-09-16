package com.devashish;

/**
 * @author Devashish Priyadarshi
 * This is exception class that is thrown whenever the expected number of inputs mismatches the provided one
 */
public class InvalidInputException extends Exception  {
	public InvalidInputException(int expectedNumberofInputs,int actualNumberOfInput) {
		super("Expected "+expectedNumberofInputs+" inputs while got "+actualNumberOfInput+".");
	}
}
