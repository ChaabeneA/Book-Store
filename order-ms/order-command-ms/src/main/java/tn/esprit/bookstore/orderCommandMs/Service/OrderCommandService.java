package tn.esprit.bookstore.orderCommandMs.Service;


import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderCommandMs.Entity.Order;

import java.util.Map;

public interface OrderCommandService {

    OrderDto add(OrderDto orderDto);

    OrderDto update(String idOrder, Map<Object,Object> fields);

    boolean delete(String idOrder);
}