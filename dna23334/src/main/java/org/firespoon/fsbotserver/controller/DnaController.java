package org.firespoon.fsbotserver.controller;

import org.firespoon.fsbotserver.model.Dna;
import org.firespoon.fsbotserver.model.Result;
import org.firespoon.fsbotserver.model.ResultFactory;
import org.firespoon.fsbotserver.service.DnaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DnaController {
    private final DnaService service;

    @Autowired
    public DnaController(DnaService service) {
        this.service = service;
    }

    @GetMapping("random")
    public Result<List<Dna>> random() {
        List<Dna> res = service.random();
        return ResultFactory.success(res);
    }
}
