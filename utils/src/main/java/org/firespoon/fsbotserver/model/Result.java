package org.firespoon.fsbotserver.model;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Map<String, Object> toMap() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("code", code);
        map.put("message", message);
        map.put("data", data);
        return map;
    }
}
