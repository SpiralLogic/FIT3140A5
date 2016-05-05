package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennings
 * @description
 */
public class InvalidInstructionException extends Exception {

    public InvalidInstructionException() {
    }

    public InvalidInstructionException(String detailMessage) {
        super(detailMessage);
    }

    public InvalidInstructionException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InvalidInstructionException(Throwable throwable) {
        super(throwable);
    }
}
