package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.exception.PropertyNotFoundException;
import org.firespoon.fsbotserver.model.CheckResult;
import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.service.CocService;
import org.firespoon.fsbotserver.utils.Ensure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CocController {
    private final CocService service;

    @Autowired
    public CocController(CocService service) {
        this.service = service;
    }

    @GetMapping("init")
    public Result<Integer> init(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId
    ) {
        Integer res = service.init(placeId, ownerId);
        Ensure.ensure(res > 0, "初始化失败");
        return ResultFactory.success(res);
    }

    @GetMapping("load_card")
    public Result<Integer> loadCard(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    ) {
        Integer res = service.loadCard(placeId, ownerId, name);
        Ensure.ensure(res > 0, "加载失败");
        return ResultFactory.success(res);
    }

    @GetMapping("save_card")
    public Result<Integer> saveCard(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    ) {
        Integer res = service.saveCard(placeId, ownerId, name);
        Ensure.ensure(res > 0, "保存失败");
        return ResultFactory.success(res);
    }

    @GetMapping("get_property")
    public Result<Integer> getProperty(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name
    ) {
        Integer res = service.getProperty(placeId, ownerId, name);
        Ensure.ensure(res != null, "未找到属性或技能");
        return ResultFactory.success(res);
    }

    @GetMapping("set_property")
    public Result<Integer> setProperty(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name,
            @RequestParam("value") Integer value
    ) {
        Integer res = service.setProperty(placeId, ownerId, name, value);
        Ensure.ensure(res > 0, "设置属性或技能失败");
        return ResultFactory.success(res);
    }

    @GetMapping("check")
    public Result<CheckResult> check(
            @RequestParam("place_id") Long placeId,
            @RequestParam("owner_id") Long ownerId,
            @RequestParam("name") String name,
            @RequestParam(value = "value", required = false)
                    Integer value
    ) {
        CheckResult res = service.check(placeId, ownerId, name, value);
        return ResultFactory.success(res);
    }
}
