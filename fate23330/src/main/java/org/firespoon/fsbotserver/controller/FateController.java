package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.FateResult;
import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.service.FateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FateController {
    private final FateService service;

    @Autowired
    public FateController(FateService service) {
        this.service = service;
    }

    @GetMapping("fate")
    public Result<List<FateResult>> fate(
            @RequestParam(value = "time", defaultValue = "1")
                    Integer time
    ) {
        List<FateResult> res = service.fate(time);
        return ResultFactory.success(res);
    }
}
