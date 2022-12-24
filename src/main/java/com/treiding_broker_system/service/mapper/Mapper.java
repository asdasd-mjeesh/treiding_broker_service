package com.treiding_broker_system.service.mapper;

import java.util.List;

public interface Mapper<T, F> {
    T map(F from);

    List<T> map (List<F> from);
}
