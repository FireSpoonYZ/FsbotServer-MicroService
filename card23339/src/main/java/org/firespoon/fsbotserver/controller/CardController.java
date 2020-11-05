package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.Card;
import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.service.CardService;
import org.firespoon.fsbotserver.utils.Ensure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CardController {
    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping("load")
    public Result<Card> load(
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    ) {
        Card res = service.loadCard(ownerId, name);
        Ensure.ensure(res != null, "未找到角色卡");
        return ResultFactory.success(res);
    }

    @PostMapping("save")
    public Result<Integer> save(@RequestBody Card card) {
        Integer res = service.saveCard(card);
        Ensure.ensure(res > 0, "保存失败");
        return ResultFactory.success(res);
    }

    @DeleteMapping("delete")
    public Result<Integer> delete(
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    ) {
        Integer res = service.deleteCard(ownerId, name);
        Ensure.ensure(res > 0, "未找到角色卡");
        return ResultFactory.success(res);
    }

    @GetMapping("get_all")
    public Result<List<Card>> getAll(
            @RequestParam("owner_id") Long ownerId
    ) {
        List<Card> res = service.getAllCard(ownerId);
        return ResultFactory.success(res);
    }
}
