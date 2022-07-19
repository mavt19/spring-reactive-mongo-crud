package com.bns.reactive.mongo.exceptions;

public class InvalidInputException extends  RuntimeException{

    /**
	 * 
	 */
	private static final long serialVersionUID = -8323451271218137253L;
	public  InvalidInputException(){
    }
    public InvalidInputException(String message){
        super(message);
    }
    public InvalidInputException(String message, Throwable cause ){
        super(message, cause);
    }
    public InvalidInputException(Throwable cause ){
        super(cause);
    }
}
