package org.firespoon.fsbotserver.utils;

import org.firespoon.fsbotserver.mapper.BaseMapper;

import java.util.List;

abstract public class MapperUtils {
    public static <T, M extends BaseMapper<T>> T unionSelect(M mapper, T param) {
        List<T> res = mapper.select(param);
        if (res.size() == 0) {
            return null;
        }
        Ensure.ensure(
                res.size() == 1,
                "结果不唯一"
        );
        return res.get(0);
    }
}
