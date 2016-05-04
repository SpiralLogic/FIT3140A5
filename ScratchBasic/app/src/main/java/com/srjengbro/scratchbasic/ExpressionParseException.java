package com.srjengbro.scratchbasic;


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
