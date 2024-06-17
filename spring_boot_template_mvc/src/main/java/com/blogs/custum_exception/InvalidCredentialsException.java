package com.blogs.custum_exception;

public class InvalidCredentialsException extends RuntimeException{
public InvalidCredentialsException(String msg)
{
	super(msg);
}
}
