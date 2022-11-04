package com.alexxsnjr.cqrseventsource.domain.query;

import java.util.List;

public interface QueryHandler<Q extends Query, R> {
    List<R> handle(Q query);
}
