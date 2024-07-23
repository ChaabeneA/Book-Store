package tn.esprit.bookstore.customerMs.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.bookstore.customerMs.Models.Order;

@FeignClient(name = "order-command-ms")
public interface OrderCommandClient {
    @PostMapping("orders/commands")
    Order AddOrder(@RequestBody Order order);
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


