package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.model.Servant;
import org.firespoon.fsbotserver.service.ServantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("servant")
public class ServantController {
    private final ServantService service;

    @Autowired
    public ServantController(ServantService service) {
        this.service = service;
    }

    @GetMapping("random")
    public Result<List<Servant>> random(
            @RequestParam(value = "time", defaultValue = "1")
                    Integer time,
            @RequestParam(value = "class", defaultValue = "")
                    String clazz,
            @RequestParam(value = "user_id", required = false)
                    Long userId,
            @RequestParam(value = "command", required = false)
                    String command
    ) {
        List<String> clazzList = Arrays.asList(clazz.split("\\|"));
        List<Servant> res = service.random(time, clazzList, userId, command);
        return ResultFactory.success(res);
    }

    @DeleteMapping("delete")
    public Result<Integer> delete(
            @RequestParam("name") String name,
            @RequestParam("clazz") String clazz
    ) {
        Integer res = service.delete(name, clazz);
        return ResultFactory.success(res);
    }

    @PostMapping("save")
    public Result<Integer> save(
            @RequestParam("name") String name,
            @RequestParam("clazz") String clazz
    ) {
        Integer res = service.save(name, clazz);
        return ResultFactory.success(res);
    }

    @GetMapping("hassan")
    public Result<List<Servant>> randomHassan(
            @RequestParam(value = "time", defaultValue = "1")
                    Integer time
    ) {
        List<Servant> res = service.randomHassan(time);
        return ResultFactory.success(res);
    }
}
