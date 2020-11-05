package org.firespoon.fsbotserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class Card {
    private Long ownerId;
    private String name;
    private Map<String, Integer> properties;

    public Card(CurrentCard currentCard, String name) {
        this.ownerId = currentCard.getOwnerId();
        this.name = name;
        this.properties = currentCard.getProperties();
    }
}
