package org.firespoon.fsbotserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.firespoon.fsbotserver.model.Servant;

import java.util.List;

@Mapper
public interface ServantMapper extends BaseMapper<Servant> {
    List<Servant> selectNameLike(String name);
}
