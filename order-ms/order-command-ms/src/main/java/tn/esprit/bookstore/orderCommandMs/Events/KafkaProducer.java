package tn.esprit.bookstore.orderCommandMs.Events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tn.esprit.bookstore.dto.Event;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {


    private final KafkaTemplate<String, Event> kafkaTemplate;
    private String topic = "order-event-topic";

    public void produceEvent(Event orderEvent) {
        log.info("///////////////////{}///////////////////", orderEvent.orderDto());
        if (orderEvent.orderDto() == null) {
            log.error("Attempting to produce null OrderDto");
            return;
        }
        kafkaTemplate.send(this.topic, orderEvent.type().toString() , orderEvent);
    }

}