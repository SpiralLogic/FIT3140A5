package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennings
 * @description Exception for when a variable does not exist
 */
public class VariableDoesNotExistException extends Throwable {
    public VariableDoesNotExistException() {
    }

    public VariableDoesNotExistException(String detailMessage) {
        super(detailMessage);
    }

    public VariableDoesNotExistException(String detailMessage, Throwable cause) {
        super(detailMessage, cause);
    }

    public VariableDoesNotExistException(Throwable cause) {
        super(cause);
    }

}
