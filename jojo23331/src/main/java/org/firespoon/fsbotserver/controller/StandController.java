package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.model.Stand;
import org.firespoon.fsbotserver.service.StandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("stand")
public class StandController {
    private final StandService service;

    @Autowired
    public StandController(StandService service) {
        this.service = service;
    }

    @GetMapping("random")
    public Result<List<Stand>> random(
            @RequestParam(value = "time", defaultValue = "1")
                    Integer time
    ) {
        List<Stand> res = service.random(time);
        return ResultFactory.success(res);
    }
}
