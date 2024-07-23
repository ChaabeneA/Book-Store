package tn.esprit.bookstore.orderms.orderQueryMs.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderms.orderQueryMs.Clients.CustomerRestClient;
import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;
import tn.esprit.bookstore.orderms.orderQueryMs.Models.Customer;
import tn.esprit.bookstore.orderms.orderQueryMs.Repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService{
    private final OrderRepository orderRepository;

    private final CustomerRestClient customerRestClient;

        @Override
    public Order add(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    @Override
    public Order update(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public boolean delete(String idOrder) {
        orderRepository.deleteById(idOrder);
        return orderRepository.existsById(idOrder);
    }

    @Override
    public Page<OrderDto> getOrders(int pageNbr, int pageSize) {
        return orderRepository.findAll(PageRequest.of(pageNbr,pageSize)).map(OrderDto::mapToOrderDto);
    }

    @Override
    public OrderDto getOrder(String id) {
        Order order=  orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("order not found"));
        return OrderDto.mapToOrderDto(order);
    }
    @Override
    public List<OrderDto> getOrdersByCustomerId(String id) {
        List<Order> orders = orderRepository.findAllByCustomerId(id);
        return orders.stream()
                .map(OrderDto::mapToOrderDto)
                .collect(Collectors.toList());
    }


   /* @Override
    public OrderDto getOrderByName(String name) {
        Order order= orderRepository.findByName(name)
                .orElseThrow(() ->new IllegalArgumentException("order not found with this name"));
        return OrderDto.mapToOrderDto(order);
    }*/
}

