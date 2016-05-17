package com.srjengbro.scratchbasic;

import com.srjengbro.scratchbasic.instructions.InstructionRunException;

/**
 * @author   Sol Jennings
 * @description Exception for invalid variable types
 */
public class VariableTypeException extends InstructionRunException {


    public VariableTypeException() {
    }

    public VariableTypeException(String detailMessage) {
        super(detailMessage);
    }

    public VariableTypeException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public VariableTypeException(Throwable throwable) {
        super(throwable);
    }
}
