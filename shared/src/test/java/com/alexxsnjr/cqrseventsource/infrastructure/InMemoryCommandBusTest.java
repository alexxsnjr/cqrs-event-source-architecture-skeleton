package com.alexxsnjr.cqrseventsource.infrastructure;


import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.alexxsnjr.cqrseventsource.domain.command.Command;
import com.alexxsnjr.cqrseventsource.domain.command.CommandHandler;
import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.InMemoryCommandBus;
import java.util.List;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class InMemoryCommandBusTest {

    @InjectMocks
    InMemoryCommandBus inMemoryCommandBus;
    @Mock
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        val handlers = List.of(MockedCommandHandler.class);
        ReflectionTestUtils.setField(inMemoryCommandBus, "handlers", handlers);
    }

    @Test
    @Tag("unit")
    void whenListedCommandIsReceived_thenDispatchItToItsHandler() {
        when(context.getBean(ArgumentMatchers.any(Class.class)))
            .thenReturn(buildMockedCommandHandler());
        assertDoesNotThrow(() -> inMemoryCommandBus.dispatch(new MockedCommand()));
        Mockito.verify(context, times(1)).getBean(ArgumentMatchers.any(Class.class));
    }

    @Test
    @Tag("unit")
    void whenNoListedCommandIsReceived_thenThrowAnException() {
        val exception = catchThrowableOfType(
            () -> inMemoryCommandBus.dispatch(new AnotherMockedCommand()),
            HandlerNotFoundException.class);
        assertEquals(HandlerNotFoundException.MESSAGE, exception.getMessage());
    }

    private CommandHandler buildMockedCommandHandler() {
        return new MockedCommandHandler();
    }

    class MockedCommandHandler implements CommandHandler<MockedCommand> {

        @Override
        public void handle(MockedCommand command) {
        }
    }

    class MockedCommand implements Command {

    }

    class AnotherMockedCommand implements Command {

    }

}