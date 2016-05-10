package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description instruction for when an invalid instruction was tried to be created
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
