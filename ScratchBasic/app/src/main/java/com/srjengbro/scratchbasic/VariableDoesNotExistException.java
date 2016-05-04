package com.srjengbro.scratchbasic;


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
