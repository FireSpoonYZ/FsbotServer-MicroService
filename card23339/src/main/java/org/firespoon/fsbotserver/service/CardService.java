package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.mapper.CardMapper;
import org.firespoon.fsbotserver.model.Card;
import org.firespoon.fsbotserver.utils.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private final CardMapper mapper;

    @Autowired
    public CardService(CardMapper mapper) {
        this.mapper = mapper;
    }

    public Card loadCard(Long ownerId, String name) {
        Card param = new Card();
        param.setOwnerId(ownerId);
        param.setName(name);
        return MapperUtils.unionSelect(mapper, param);
    }

    public Integer saveCard(Card card) {
        return mapper.update(card);
    }

    public Integer deleteCard(Long ownerId, String name) {
        Card card = loadCard(ownerId, name);
        return mapper.delete(card);
    }

    public List<Card> getAllCard(Long ownerId) {
        Card param = new Card();
        param.setOwnerId(ownerId);
        return mapper.select(param);
    }
}
