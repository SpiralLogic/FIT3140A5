package com.srjengbro.scratchbasic;

/**
 * @author      Sol Jennings
 * @description Thrown when an expression creation fails
 */
public class ExpressionParseException extends Exception {
    public ExpressionParseException() {
    }

    public ExpressionParseException(String detailMessage) {
        super(detailMessage);
    }

    public ExpressionParseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public ExpressionParseException(Throwable throwable) {
        super(throwable);
    }
}
