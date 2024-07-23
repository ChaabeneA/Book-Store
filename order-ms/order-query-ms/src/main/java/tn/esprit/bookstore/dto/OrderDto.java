package tn.esprit.bookstore.dto;


import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;
import tn.esprit.bookstore.orderms.orderQueryMs.Models.Customer;

import java.time.LocalDateTime;

public record OrderDto(String id, String price, String quantity, String address , String customerId , Customer customer, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static Order mapToOrder(OrderDto orderDto) {
        return new Order(orderDto.id(), orderDto.price(),orderDto.address(), orderDto.customerId(),orderDto.customer(), orderDto.quantity(), orderDto.createdAt(), orderDto.updatedAt());
    }

    public static OrderDto mapToOrderDto(Order order) {
        return new OrderDto(order.getId(), order.getPrice(),order.getQuantity(), order.getAddress(), order.getCustomerId(),order.getCustomer() , order.getCreatedAt(), order.getUpdatedAt());
    }
}