package org.firespoon.fsbotserver.model;

import lombok.Data;

import java.util.Set;

@Data
public class FateResult {
    private String magicPowerLevel;
    private String magicCircuitLevel;
    private Set<String> attributes;
    private String origin;
    private Integer magicPower;
    private String birth;
}
