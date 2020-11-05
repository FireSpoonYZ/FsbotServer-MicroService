package org.firespoon.fsbotserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.firespoon.fsbotserver.model.Dna;

import java.util.List;

@Mapper
public interface DnaMapper extends BaseMapper<Dna> {
    List<Dna> draw(Integer[] time);
}
