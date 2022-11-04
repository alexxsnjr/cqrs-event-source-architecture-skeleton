package com.alexxsnjr.cqrseventsource.infrastructure;

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.alexxsnjr.cqrseventsource.domain.error.HandlerNotFoundException;
import com.alexxsnjr.cqrseventsource.domain.query.Query;
import com.alexxsnjr.cqrseventsource.domain.query.QueryHandler;
import com.alexxsnjr.cqrseventsource.infrastructure.bus.InMemoryQueryBus;
import java.util.Collections;
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
class InMemoryQueryBusTest {

    @InjectMocks
    InMemoryQueryBus inMemoryQueryBus;
    @Mock
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        val handlers = List.of(MockedQueryHandler.class);
        ReflectionTestUtils.setField(inMemoryQueryBus, "handlers", handlers);
    }

    @Test
    @Tag("unit")
    void whenListedQueryIsReceived_thenDispatchItToItsHandler() {
        when(context.getBean(ArgumentMatchers.any(Class.class)))
            .thenReturn(buildMockedQueryHandler());
        assertDoesNotThrow(() -> inMemoryQueryBus.ask(new MockedQuery()));
        Mockito.verify(context, times(1)).getBean(ArgumentMatchers.any(Class.class));
    }

    @Test
    @Tag("unit")
    void whenNoListedQueryIsReceived_thenThrowAnException() {
        val exception = catchThrowableOfType(
            () -> inMemoryQueryBus.ask(new AnotherMockedQUery()),
            HandlerNotFoundException.class);
        assertEquals(HandlerNotFoundException.MESSAGE, exception.getMessage());
    }

    private QueryHandler buildMockedQueryHandler() {
        return new MockedQueryHandler();
    }

    class MockedQueryHandler implements QueryHandler<MockedQuery, List> {

        @Override
        public List handle(MockedQuery query) {
            return Collections.emptyList();
        }
    }

    class MockedQuery implements Query {

    }

    class AnotherMockedQUery implements Query {

    }

}