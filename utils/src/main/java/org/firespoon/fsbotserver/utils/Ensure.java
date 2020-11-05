package org.firespoon.fsbotserver.utils;

import org.firespoon.fsbotserver.exception.EnsureException;

abstract public class Ensure {
    public static void ensure(
            boolean condition,
            String message
    ) {
        if (!condition) {
            throw new EnsureException(message);
        }
    }
}
