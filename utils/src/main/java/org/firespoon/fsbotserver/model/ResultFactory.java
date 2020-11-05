package org.firespoon.fsbotserver.model;

public class ResultFactory {
    public static <T> Result<T> create(Integer code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> success(T data) {
        return create(200, "", data);
    }

    public static <T> Result<T> success(String message, T data) {
        return create(200, message, data);
    }

    public static <T> Result<T> fail() {
        return create(400, "", null);
    }

    public static <T> Result<T> fail(String message) {
        return create(500, message, null);
    }
}
