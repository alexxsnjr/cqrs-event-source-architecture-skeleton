package com.alexxsnjr.cqrseventsource.infrastructure;

import com.alexxsnjr.cqrseventsource.domain.event.DomainEvent;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collation = "eventStore")
public class EventModel {

    @Id
    private String id;
    private Date timeStamp;
    private String aggregateId;
    private String aggregateType;
    private int version;
    private String eventType;
    private DomainEvent domainEvent;

}
