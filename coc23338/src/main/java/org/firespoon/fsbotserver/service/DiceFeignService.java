package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.model.DiceResult;
import org.firespoon.fsbotserver.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient("dice")
public interface DiceFeignService {
    @GetMapping("dice")
    Result<DiceResult> dice(
            @RequestParam("dice_exp") String diceExp
    );
}
