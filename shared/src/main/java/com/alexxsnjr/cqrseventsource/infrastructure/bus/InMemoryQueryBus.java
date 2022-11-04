package com.alexxsnjr.cqrseventsource.infrastructure.bus;

import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.error.QueryHandlerExecutionException;
import com.alexxsnjr.cqrseventsource.domain.query.Query;
import com.alexxsnjr.cqrseventsource.domain.query.QueryBus;
import com.alexxsnjr.cqrseventsource.domain.query.QueryHandler;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public final class InMemoryQueryBus implements QueryBus {

    private static List<Class<? extends QueryHandler>> handlers;
    private final ApplicationContext context;

    @PostConstruct
    public void loadImplementations() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
            .setUrls(ClasspathHelper.forPackage("com.alexxsnjr"))
            .setScanners(new SubTypesScanner())
            .filterInputsBy(new FilterBuilder().includePackage(
                "com.alexxsnjr")));

        Set<Class<? extends QueryHandler>> subTypes = reflections.getSubTypesOf(QueryHandler.class);
        handlers = List.copyOf(subTypes);
    }

    @Override
    public Object ask(Query query) throws QueryHandlerExecutionException {
        val queryHandler = handlers.stream()
            .filter(s -> s.getGenericInterfaces()[0].toString()
                .contains(query.getClass()
                    .getSimpleName()))
            .findFirst()
            .orElseThrow(HandlerNotFoundException::new);

        val handler = context.getBean(queryHandler);
        val result = handler.handle(query);
        return result;
    }

}
