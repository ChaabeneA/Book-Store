package tn.esprit.bookstore.orderms.orderQueryMs.Events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tn.esprit.bookstore.dto.Event;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {

    private final OrderEventHandler orderEventHandler;
    private final String topic = "order-event-topic";

    @KafkaListener(topics = topic, groupId = "my-group")
    public void consume(ConsumerRecord<String, Event> consumerRecord) {

        Event event = consumerRecord.value();

        log.info("\n Consumed Event of type : {} \n published on topic at : {} \n Data value is : {}", event.type(), event.eventCreatedAt(), event.orderDto() );
        log.info("Received OrderDto: {}", event.orderDto());
        if (event.orderDto() == null) {
            log.error("OrderDto is null");
            throw new IllegalArgumentException("OrderDto should not be null");
        }else {
        switch (consumerRecord.key()) {
            case "CREATED_ORDER_EVENT":
                orderEventHandler.handleOrderCreatedEvent(event.orderDto());
                break;
            case "UPDATED_ORDER_EVENT":
                orderEventHandler.handleOrderUpdatedEvent(event.orderDto());
                break;
            case "DELETED_ORDER_EVENT":
                orderEventHandler.handleOrderDeletedEvent(event.orderDto().id());
                break;
            default:
                log.info("Event ignored");
                break;
        }
        }

    }
}