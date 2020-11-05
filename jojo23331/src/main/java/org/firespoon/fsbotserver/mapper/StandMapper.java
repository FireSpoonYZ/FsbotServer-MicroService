package org.firespoon.fsbotserver.mapper;

import org.firespoon.fsbotserver.model.Stand;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StandMapper extends BaseMapper<Stand> {
    List<Stand> random(Integer time);
}
