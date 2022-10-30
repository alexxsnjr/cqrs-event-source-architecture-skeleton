package com.alexxsnjr.cqrseventsource.domain.query;

import com.alexxsnjr.cqrseventsource.domain.error.QueryHandlerExecutionException;

public interface QueryBus {
    <R> R ask(Query query) throws QueryHandlerExecutionException;
}
