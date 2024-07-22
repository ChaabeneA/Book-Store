package tn.esprit.bookstore.orderCommandMs.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.bookstore.dto.Event;
import tn.esprit.bookstore.dto.EventType;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderCommandMs.Entity.Order;
import tn.esprit.bookstore.orderCommandMs.Repository.OrderRepository;
import tn.esprit.bookstore.orderCommandMs.Events.KafkaProducer;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class OrderCommandServiceImpl implements OrderCommandService {
    @Autowired
    private  OrderRepository orderRepository;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public OrderDto add(OrderDto orderDto) {
        Order order = OrderDto.mapToOrder(orderDto);
        order.setCreatedAt(LocalDateTime.now());
        orderDto = OrderDto.mapToOrderDto(orderRepository.save(order));
        kafkaProducer.produceEvent(new Event(EventType.CREATED_ORDER_EVENT, orderDto,LocalDateTime.now()));
        return orderDto;
    }

    @Override
    public OrderDto update(String idOrder, Map<Object, Object> fields) {
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + idOrder));

        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, (String) key);
            field.setAccessible(true);

            if(field.getType().equals(LocalDate.class)){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, order , localDate);
            }else {
                ReflectionUtils.setField(field, order , value);
            }

        });
        order.setUpdatedAt(LocalDateTime.now());
        OrderDto orderDto = OrderDto.mapToOrderDto(orderRepository.save(order));
        kafkaProducer.produceEvent(new Event(EventType.UPDATED_ORDER_EVENT, orderDto,LocalDateTime.now()));
        return orderDto;
    }

    @Override
    public boolean delete(String idOrder) {

        OrderDto orderDto = OrderDto.mapToOrderDto(orderRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + idOrder)));

        orderRepository.deleteById(idOrder);
        kafkaProducer.produceEvent(new Event(EventType.DELETED_ORDER_EVENT, orderDto,LocalDateTime.now()));

        return orderRepository.existsById(idOrder);
    }


}