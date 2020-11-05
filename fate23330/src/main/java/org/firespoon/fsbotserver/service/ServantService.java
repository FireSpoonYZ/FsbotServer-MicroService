package org.firespoon.fsbotserver.service;

import org.firespoon.fsbotserver.mapper.ServantMapper;
import org.firespoon.fsbotserver.model.Servant;
import org.firespoon.fsbotserver.utils.RandomUtils;
import org.firespoon.fsbotserver.utils.ServantUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServantService {
    private final ServantMapper mapper;

    @Autowired
    public ServantService(ServantMapper mapper) {
        this.mapper = mapper;
    }

    public List<Servant> random(
            Integer time,
            List<String> clazzList,
            Long userId,
            String command
    ) {
        List<Servant> params = new LinkedList<>();
        for (String clazz: clazzList) {
            Servant param = new Servant();
            param.setClazz(ServantUtils.toHump(clazz));
            params.add(param);
        }

        List<Servant> servants = mapper.selectAll(params);

        if (command != null && userId != null) {
            String seedStr = String.format("%s_%s", command, userId);
            int seed = seedStr.hashCode();
            Random random = new Random(seed);
            Collections.shuffle(servants, random);
        } else {
            Collections.shuffle(servants);
        }

        if (time > servants.size()) {
            time = servants.size();
        }
        return servants.subList(0, time);
    }

    public Integer delete(String name, String clazz) {
        clazz = ServantUtils.toHump(clazz);
        Servant servant = new Servant();
        servant.setName(name);
        servant.setClazz(clazz);
        return mapper.delete(servant);
    }

    public Integer save(String name, String clazz) {
        clazz = ServantUtils.toHump(clazz);
        Servant servant = new Servant();
        servant.setName(name);
        servant.setClazz(clazz);
        return mapper.insert(servant);
    }

    public List<Servant> randomHassan(Integer time) {
        Random random = new Random();
        int num = random.nextInt() % 100;

        String name;
        if (0 <= num && num <= 2) {
            name = "狂信徒";
        } else if (3 <= num && num <= 5) {
            name = "初代哈桑";
        } else {
            name = "哈桑";
        }

        List<Servant> hassans = mapper.selectNameLike(name);

        Collections.shuffle(hassans, random);
        return hassans.subList(0, time);
    }
}
