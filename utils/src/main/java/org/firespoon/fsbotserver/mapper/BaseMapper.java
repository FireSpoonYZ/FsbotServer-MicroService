package org.firespoon.fsbotserver.mapper;

import java.util.List;

public interface BaseMapper<T> {
    Integer insert(T record);
    Integer insertAll(List<T> records);
    Integer delete(T record);
    Integer deleteAll(List<T> records);
    Integer update(T record);
    Integer updateAll(List<T> records);
    List<T> select(T records);
    List<T> selectAll(List<T> records);
}
