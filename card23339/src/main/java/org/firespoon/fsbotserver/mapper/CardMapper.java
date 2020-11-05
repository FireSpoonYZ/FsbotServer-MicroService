package org.firespoon.fsbotserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.firespoon.fsbotserver.model.Card;

@Mapper
public interface CardMapper extends BaseMapper<Card> {
}
