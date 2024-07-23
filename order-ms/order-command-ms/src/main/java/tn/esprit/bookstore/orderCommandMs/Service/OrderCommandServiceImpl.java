package tn.esprit.bookstore.orderCommandMs.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.bookstore.dto.Event;
import tn.esprit.bookstore.dto.EventType;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderCommandMs.Clients.CustomerRestClient;
import tn.esprit.bookstore.orderCommandMs.Entity.Order;
import tn.esprit.bookstore.orderCommandMs.Models.Customer;
import tn.esprit.bookstore.orderCommandMs.Repository.OrderRepository;
import tn.esprit.bookstore.orderCommandMs.Events.KafkaProducer;
import tn.esprit.bookstore.orderCommandMs.Utility.ConversionUtil;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderCommandServiceImpl implements OrderCommandService {

    private final OrderRepository orderRepository;

    private final KafkaProducer kafkaProducer;
    private final CustomerRestClient customerRestClient;

    @Override
    public OrderDto add(OrderDto orderDto) {
        Boolean isCustomerValid = false;
        if (orderDto.customerId() != null) {
            Long id = ConversionUtil.stringToLong(orderDto.customerId());
            isCustomerValid = customerRestClient.validateCustomer(id);
        } else {
            isCustomerValid = false;
        }

        if (isCustomerValid) {
            // Existing customer, proceed with order creation
            Order order = OrderDto.mapToOrder(orderDto);
            Customer customer=customerRestClient.findCustomerById(order.getCustomerId());
            order.setCustomer(customer);
            order.setCreatedAt(LocalDateTime.now());
            orderDto = OrderDto.mapToOrderDto(orderRepository.save(order));
            kafkaProducer.produceEvent(new Event(EventType.CREATED_ORDER_EVENT, orderDto, LocalDateTime.now()));
            return orderDto;
        } else {
            // Customer does not exist, check if new customer details are present
            if (orderDto.customer() != null) {
                Customer newCustomer = orderDto.customer();
                customerRestClient.createCustomer(newCustomer);
                String id=customerRestClient.findCustomerByEmail(newCustomer.getEmail()).getId();
                Order order = OrderDto.mapToOrder(orderDto);
                order.setCustomerId(id);
                order.setCreatedAt(LocalDateTime.now());
                orderDto = OrderDto.mapToOrderDto(orderRepository.save(order));
                kafkaProducer.produceEvent(new Event(EventType.CREATED_ORDER_EVENT, orderDto, LocalDateTime.now()));
                return orderDto;
            }
            return null;
        }
    }
    @Override
    public OrderDto update(Long idOrder, Map<Object, Object> fields,String customerId) {
        Long id= ConversionUtil.stringToLong(customerId);
        Boolean isCustomerValid = customerRestClient.validateCustomer(id);
        if (!isCustomerValid) {
            throw new IllegalArgumentException("Invalid customer ID: " + customerId);
        }

        // Find the order
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + idOrder));

        // Update fields in the order
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Order.class, (String) key);
            field.setAccessible(true);

            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, order, localDate);
            } else {
                ReflectionUtils.setField(field, order, value);
            }
        });

        // Update the customer ID
        order.setCustomerId(customerId);

        // Set the updatedAt timestamp
        order.setUpdatedAt(LocalDateTime.now());

        // Save the updated order
        OrderDto orderDto = OrderDto.mapToOrderDto(orderRepository.save(order));

        // Produce the event to Kafka
        kafkaProducer.produceEvent(new Event(EventType.UPDATED_ORDER_EVENT, orderDto, LocalDateTime.now()));
        List<Order> customerOrders = orderRepository.findAllByCustomerId(customerId);
        if (customerOrders.isEmpty()) {
            // Delete the customer if no other orders exist
            customerRestClient.deleteCustomer(ConversionUtil.stringToLong(customerId));
        }
        return orderDto;
    }

    @Override
    public boolean delete(Long idOrder) {
        // Find the order and map to DTO
        Order order = orderRepository.findById(idOrder)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + idOrder));
        OrderDto orderDto = OrderDto.mapToOrderDto(order);

        // Get the customer ID associated with the order
        String customerId = order.getCustomerId();

        // Delete the order
        orderRepository.deleteById(idOrder);

        // Produce the deleted order event
        kafkaProducer.produceEvent(new Event(EventType.DELETED_ORDER_EVENT, orderDto, LocalDateTime.now()));

        // Check if the customer has any other orders
        List<Order> customerOrders = orderRepository.findAllByCustomerId(customerId);
        if (customerOrders.isEmpty()) {
            // Delete the customer if no other orders exist
            customerRestClient.deleteCustomer(ConversionUtil.stringToLong(customerId));
        }

        // Return whether the order still exists
        return orderRepository.existsById(idOrder);
    }



}