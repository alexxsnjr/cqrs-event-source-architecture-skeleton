package com.alexxsnjr.cqrseventsource.domain.error;

public final class QueryHandlerExecutionException extends RuntimeException {
    public QueryHandlerExecutionException(Throwable cause) {
        super(cause);
    }
}
