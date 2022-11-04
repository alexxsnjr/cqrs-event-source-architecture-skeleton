package com.alexxsnjr.cqrseventsource.infrastructure.bus;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import com.alexxsnjr.cqrseventsource.domain.command.CommandBus;
import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;
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
public final class InMemoryCommandBus implements CommandBus {

    private static List<Class<? extends CommandHandler>> handlers;
    private final ApplicationContext context;

    @PostConstruct
    public void loadImplementations() {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("com.alexxsnjr"))
                .setScanners(new SubTypesScanner())
                .filterInputsBy(new FilterBuilder().includePackage("com.alexxsnjr")));

        Set<Class<? extends CommandHandler>> subTypes = reflections.getSubTypesOf(CommandHandler.class);
        handlers = List.copyOf(subTypes);
    }

    @Override
    public void dispatch(Command command) throws HandlerNotFoundException {
        writeDebugLogs(command);

        val commandHandler = handlers.stream()
                .filter(s -> s.getGenericInterfaces()[0].toString()
                        .contains(command.getClass()
                                .getSimpleName()))
                .findFirst()
                .orElseThrow(HandlerNotFoundException::new);

        val handler = context.getBean(commandHandler);
        handler.handle(command);
    }

    private void writeDebugLogs(Command command) {
        log.debug("Available handlers: {}", handlers.size());
        handlers.forEach(h -> log.debug(h.getGenericInterfaces()[0].toString()));
        log.debug("Received command: {}", command.getClass()
                .getSimpleName());
    }

}
