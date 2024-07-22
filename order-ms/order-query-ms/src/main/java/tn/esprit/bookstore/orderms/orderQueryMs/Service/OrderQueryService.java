package tn.esprit.bookstore.orderms.orderQueryMs.Service;


import org.springframework.data.domain.Page;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;


public interface OrderQueryService {
    Order add(Order order);
    Order update(Order order);
    boolean delete(long idOrder);


    Page<OrderDto> getOrders(int pageNbr, int pageSize);

    OrderDto getOrder(long id);

    //OrderDto getOrderByCustomerName(String name);

}
