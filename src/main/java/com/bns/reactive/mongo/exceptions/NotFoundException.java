package com.bns.reactive.mongo.exceptions;

public class NotFoundException extends  RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4397428813023613300L;
	
	public NotFoundException(){

    }
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(String message, Throwable cause ){
        super(message, cause);
    }
    public NotFoundException(Throwable cause ){
        super(cause);
    }
}
