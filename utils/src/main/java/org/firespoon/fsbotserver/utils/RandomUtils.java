package org.firespoon.fsbotserver.utils;

import java.util.Random;

abstract public class RandomUtils {
    private static final Random random = new Random();

    public static int random(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }
}
