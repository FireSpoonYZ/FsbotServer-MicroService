package org.firespoon.fsbotserver.exception;

public class PropertyNotFoundException extends Exception {
    private final String name;
    public PropertyNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
