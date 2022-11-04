package com.alexxsnjr.cqrseventsource.infrastructure.bus;

import com.alexxsnjr.cqrseventsource.domain.error.QueryHandlerExecutionException;
import com.alexxsnjr.cqrseventsource.domain.query.Query;
import com.alexxsnjr.cqrseventsource.domain.query.QueryBus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class QueryBusController {

    private final QueryBus queryBus;

    protected <R> R ask(Query query) throws QueryHandlerExecutionException {
        return queryBus.ask(query);
    }

}
