package org.firespoon.fsbotserver.utils;

import java.util.Arrays;
import java.util.List;

abstract public class ServantUtils {
    private static final List<String> clazzList = Arrays.asList(
            "Saber",
            "Archer",
            "Lancer",
            "Rider",
            "Caster",
            "Assassin",
            "Berserker",
            "Shielder",
            "Ruler",
            "Avenger",
            "Alterego",
            "MoonCancer",
            "Foreigner"
    );

    public static String toHump(String clazz) {
        String lClazz = clazz.toLowerCase();
        for (String res: clazzList) {
            if (res.toLowerCase().equals(lClazz)) {
                return res;
            }
        }

        return null;
    }
}
