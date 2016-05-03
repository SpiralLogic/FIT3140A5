package com.srjengbro.scratchbasic;

/**
 * @author: Sol Jennings 26356015
 * @date: 3/05/16
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
