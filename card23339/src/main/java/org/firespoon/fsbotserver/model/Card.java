package org.firespoon.fsbotserver.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Card {
    private Long ownerId;
    private String name;
    private Map<String, Integer> properties;
}
