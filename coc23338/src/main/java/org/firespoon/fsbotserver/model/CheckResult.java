package org.firespoon.fsbotserver.model;

import lombok.Data;

@Data
public class CheckResult {
    private DiceResult diceResult;
    private Integer property;
    private String result;
}
