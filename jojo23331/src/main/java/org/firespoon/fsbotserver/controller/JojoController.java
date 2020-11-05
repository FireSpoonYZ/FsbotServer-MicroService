package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.JojoResult;
import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.service.JojoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JojoController {
    private final JojoService service;

    @Autowired
    public JojoController(JojoService service) {
        this.service = service;
    }

    @GetMapping("jojo")
    public Result<List<JojoResult>> jojo(
            @RequestParam(value = "time", defaultValue = "1")
                    Integer time
    ) {
        List<JojoResult> res = service.jojo(time);
        return ResultFactory.success(res);
    }
}
