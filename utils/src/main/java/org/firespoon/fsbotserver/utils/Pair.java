package org.firespoon.fsbotserver.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pair<T, TT> {
    private T first;
    private TT second;
}
