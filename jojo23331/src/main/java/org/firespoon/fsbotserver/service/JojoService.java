package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.model.JojoResult;
import org.firespoon.fsbotserver.utils.RandomUtils;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class JojoService {
    private String randomLevel() {
        int[] weight = new int[]{10, 15, 20, 15, 10};
        String[] level = new String[]{"A", "B", "C", "D", "E"};
        int sum = 0;
        for (int i : weight) {
            sum += i;
        }
        int seed = RandomUtils.random(1, sum);
        for (int i = 0; i < weight.length; ++i) {
            seed -= weight[i];
            if (seed <= 0) {
                return level[i];
            }
        }

        return "Error";
    }

    public List<JojoResult> jojo(Integer time) {
        List<JojoResult> res = new LinkedList<>();
        for (int i = 0; i < time; ++i) {
            JojoResult jojoResult = new JojoResult();
            jojoResult.setPower(randomLevel());
            jojoResult.setSpeed(randomLevel());
            jojoResult.setRange(randomLevel());
            jojoResult.setLasting(randomLevel());
            jojoResult.setPrecision(randomLevel());
            jojoResult.setGrowth(randomLevel());
            jojoResult.setAbility(randomLevel());
            res.add(jojoResult);
        }
        return res;
    }
}
