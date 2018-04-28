package com.revature.media;
import java.lang.Exception;

public class MediaException extends Exception {
	
	public MediaException() {
		super("invalid media type");
	}
		
}
