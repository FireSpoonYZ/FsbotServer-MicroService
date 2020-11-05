package org.firespoon.fsbotserver.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class CurrentCard {
    private Long placeId;
    private Long ownerId;
    private Map<String, Integer> properties;

    public CurrentCard(Card card, Long placeId) {
        this.placeId = placeId;
        this.ownerId = card.getOwnerId();
        this.properties = card.getProperties();
    }
}
