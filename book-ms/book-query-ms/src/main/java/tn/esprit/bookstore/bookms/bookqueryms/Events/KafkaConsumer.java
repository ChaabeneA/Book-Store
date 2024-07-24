package tn.esprit.bookstore.bookms.bookqueryms.Events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.bookstore.bookms.dto.Event;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final BookEventHandler bookEventHandler;
    private final String topic = "book-event-topic";

    @KafkaListener(topics = topic, groupId = "my-group")
    public void consume(ConsumerRecord<String, Event> consumerRecord) {

        Event event = consumerRecord.value();

        log.info("\n Consumed Event of type : {} \n published on topic at : {} \n Data value is : {}", event.type(), event.eventCreatedAt(), event.bookDto() );
        log.info("Received BookDto: {}", event.bookDto());
        if (event.bookDto() == null) {
            log.error("BookDto is null");
            throw new IllegalArgumentException("BookDto should not be null");
        }else {
        switch (consumerRecord.key()) {
            case "CREATED_BOOK_EVENT":
                bookEventHandler.handleBookCreatedEvent(event.bookDto());
                break;
            case "UPDATED_BOOK_EVENT":
                bookEventHandler.handleBookUpdatedEvent(event.bookDto());
                break;
            case "DELETED_BOOK_EVENT":
                bookEventHandler.handleBookDeletedEvent(event.bookDto().id());
                break;
            default:
                log.info("Event ignored");
                break;
        }
        }

    }
}