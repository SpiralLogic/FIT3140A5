package com.srjengbro.scratchbasic.instructions;

/**
 * Created by Complex on 1/05/2016.
 */
public class InstructionRunException extends Exception {
    public InstructionRunException() {
    }

    public InstructionRunException(String detailMessage) {
        super(detailMessage);
    }

    public InstructionRunException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public InstructionRunException(Throwable throwable) {
        super(throwable);
    }
}
