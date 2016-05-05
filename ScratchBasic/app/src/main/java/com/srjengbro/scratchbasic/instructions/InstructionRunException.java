package com.srjengbro.scratchbasic.instructions;

/**
 * @author      Sol Jennings
 * @description Exception for when an instruction has an error
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
