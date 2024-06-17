package com.blogs.dto;

import java.time.LocalDateTime;

public class ApiResponse {
private String message;
private LocalDateTime timeStamp;
public ApiResponse()
{
	System.out.println("in default const of "+getClass());
}
public ApiResponse(String message)
{
	super();
	this.message=message;
}

public LocalDateTime getTimeStamp() {
	return timeStamp;
}
public void setTimeStamp(LocalDateTime timeStamp) {
	this.timeStamp = timeStamp;
}

}
