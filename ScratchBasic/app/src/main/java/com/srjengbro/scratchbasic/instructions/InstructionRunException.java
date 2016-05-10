package com.srjengbro.scratchbasic.instructions;

/**
 * @author      Sol Jennigns & Giles Browne
 * @description Exception for when an instruction when run
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
