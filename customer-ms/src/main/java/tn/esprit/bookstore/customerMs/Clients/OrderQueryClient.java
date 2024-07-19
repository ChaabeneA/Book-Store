package tn.esprit.bookstore.customerMs.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.bookstore.customerMs.Models.Order;

import java.util.List;

@FeignClient(name = "Order-Query-Microservice")
public interface OrderQueryClient {
    @GetMapping("orders/queries/customer/{id}")
    List<Order> allOrdersByCustomerId(@PathVariable String id);
   /* default Order getDefaultOrder(String id,Exception exception)
    {
        Order order=new Order();
        order.setId(id);
        order.setQuantity(null);
        order.setPrice(null);
        order.setAddress("not available");
        return order;
    }*/
}
