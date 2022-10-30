package com.alexxsnjr.cqrseventsource.domain.query;

public interface QueryHandler<Q extends Query, R extends Result> {
    R handle(Q query);
}
