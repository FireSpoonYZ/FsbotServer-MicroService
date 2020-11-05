package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.model.FateResult;
import org.firespoon.fsbotserver.utils.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class FateService {

    private String randomMagicPowerOrCircuitLevel() {
        int rnd = RandomUtils.random(1, 30);
        if (1 <= rnd && rnd <= 2) {
            return "A";
        } else if (1 <= rnd && rnd <= 14) {
            return "B";
        } else if (15 <= rnd && rnd <= 26) {
            return "C";
        } else if (27 <= rnd && rnd <= 29) {
            return "D";
        } else {
            return "E";
        }
    }

    private int randomAttributeTypeNum() {
        int rnd = RandomUtils.random(1, 30);
        if (1 <= rnd && rnd <= 26) {
            return 1;
        } else if (rnd == 27) {
            return 2;
        } else if (rnd == 28) {
            return 3;
        } else if (rnd == 29) {
            return 4;
        } else {
            return 5;
        }
    }

    private String randomAttribute() {
        int rnd = RandomUtils.random(1, 30);
        if (1 <= rnd && rnd <= 5) {
            return "地";
        } else if (6 <= rnd && rnd <= 10) {
            return "水";
        } else if (11 <= rnd && rnd <= 15) {
            return "火";
        } else if (16 <= rnd && rnd <= 20) {
            return "风";
        } else if (21 <= rnd && rnd <= 25) {
            return "空";
        } else if (26 <= rnd && rnd <= 28) {
            return "变质";
        } else if (rnd == 29) {
            return "虚数";
        } else {
            return "无或自制属性";
        }
    }

    private Set<String> randomAttributes() {
        int attributeTypeNum = randomAttributeTypeNum();
        Set<String> attributes = new HashSet<>();
        for (int i = 0; i < attributeTypeNum; ++i) {
            while (true) {
                String attribute = randomAttribute();
                if (!attributes.contains(attribute)) {
                    attributes.add(attribute);
                    break;
                }
            }
        }

        return attributes;
    }

    private String randomOrigin() {
        int rnd = RandomUtils.random(1, 4);
        if (rnd == 1) {
            return "野兽";
        } else if (rnd == 2) {
            return "冷兵器";
        } else if (rnd == 3) {
            return "特性";
        } else {
            return "动作";
        }
    }

    private int randomMagicPower(String magicPowerLevel) {
        int level;
        switch (magicPowerLevel) {
            case "A":
                level = 5;
                break;
            case "B":
                level = 4;
                break;
            case "C":
                level = 3;
                break;
            case "D":
                level = 2;
                break;
            default:
                level = 1;
                break;
        }
        int magicPower = 0;
        for (int i = 0; i < level; ++i) {
            magicPower += RandomUtils.random(1, 8);
        }
        if ((double) magicPower < (double) level * 4.5) {
            if (level == 5 || level == 4) {
                magicPower += 10;
            } else if (level == 3 || level == 2) {
                magicPower += 5;
            }
        }
        return magicPower;
    }

    private String randomBirth() {
        int rnd = RandomUtils.random(1, 4);
        if (rnd == 1) {
            return "魔术师世家";
        } else if (rnd == 2) {
            return "没落的魔术师家族";
        } else if (rnd == 3) {
            return "独行魔术师";
        } else {
            return "普通人";
        }
    }

    public List<FateResult> fate(Integer time) {
        List<FateResult> res = new LinkedList<>();
        for (int i = 0; i < time; ++i) {
            String magicPowerLevel = randomMagicPowerOrCircuitLevel();
            String magicCircuitLevel = randomMagicPowerOrCircuitLevel();
            Set<String> attributes = randomAttributes();
            String origin = randomOrigin();
            Integer magicPower = randomMagicPower(magicPowerLevel);
            String birth = randomBirth();

            FateResult fateResult = new FateResult();
            fateResult.setMagicPowerLevel(magicPowerLevel);
            fateResult.setMagicCircuitLevel(magicCircuitLevel);
            fateResult.setAttributes(attributes);
            fateResult.setOrigin(origin);
            fateResult.setMagicPower(magicPower);
            fateResult.setBirth(birth);

            res.add(fateResult);
        }

        return res;
    }
}
