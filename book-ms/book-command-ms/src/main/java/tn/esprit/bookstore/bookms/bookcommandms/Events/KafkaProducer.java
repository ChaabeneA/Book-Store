package tn.esprit.bookstore.bookms.bookcommandms.Events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.bookstore.bookms.dto.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, Event> kafkaTemplate;
    private String topic = "book-event-topic";

    public void produceEvent(Event bookEvent) {
        if (bookEvent.bookDto() == null) {
            log.error("Attempting to produce null BookDto");
            return;
        }
        kafkaTemplate.send(this.topic, bookEvent.type().toString() , bookEvent);
    }

}