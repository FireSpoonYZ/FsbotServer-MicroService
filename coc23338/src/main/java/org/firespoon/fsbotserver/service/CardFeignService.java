package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.model.Card;
import org.firespoon.fsbotserver.model.CurrentCard;
import org.firespoon.fsbotserver.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@FeignClient(value = "card")
public interface CardFeignService {
    @GetMapping("load")
    Result<Card> load(
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    );

    @PostMapping("save")
    Result<Integer> save(@RequestBody Card currentCard);

    @GetMapping("get_all")
    Result<List<Card>> getAll(
            @RequestParam("owner_id") Long ownerId
    );
}
