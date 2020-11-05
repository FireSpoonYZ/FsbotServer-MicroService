package org.firespoon.fsbotserver.exception;

public class ExpressionFormatException extends IllegalArgumentException {
    public ExpressionFormatException() {
        super("表达式格式错误");
    }
}
