package com.apps.custom_excpetions;

public class InvalidCredentialsException extends RuntimeException {
	public InvalidCredentialsException(String mesg) {
		super(mesg);
	}
}
