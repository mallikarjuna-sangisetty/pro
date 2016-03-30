
package com.project.traffic.exception;


public class StandardException extends Exception{

    
    public StandardException(String message) {
        super(message);
    }

    public StandardException(String message, Throwable t) {
        super(message, t);
    }
    
}
