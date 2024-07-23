package tn.esprit.bookstore.orderms.orderQueryMs.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;
import tn.esprit.bookstore.orderms.orderQueryMs.Service.OrderQueryService;

import java.util.List;

@RestController
@RequestMapping("/orders/queries")
@RequiredArgsConstructor
public class OrderQueryController {

    private final OrderQueryService orderQueryService;

    /*@GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderQueryService.getOrderById(id);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderQueryService.getAllOrders();
    }
    @GetMapping("customer/{id}")
    public List<Order> orderListByCustomer(@PathVariable String id){
        return orderQueryService.orderListByCustomerId(id);
    }
    @GetMapping("single/{id}")
    public Order orderById(@PathVariable Long id)
    {
        return orderQueryService.orderById(id);

    }*/
    ////////////////////////////////////////////////////////////////////
    @GetMapping
    public Page<OrderDto> getOrders(int pageNbr, int pageSize){
        return orderQueryService.getOrders(pageNbr,pageSize);
    }

    @GetMapping("{id}")
    public OrderDto getOrder(@PathVariable String id){
        return orderQueryService.getOrder(id);
    }
    @GetMapping("customer/{id}")
    List<OrderDto> allOrdersByCustomerId(@PathVariable String id){
        return orderQueryService.getOrdersByCustomerId(id);
    }


   /* @GetMapping("name/{name}")
    public OrderDto getOrder(@PathVariable String name){
        return orderQueryService.getOrderByName(name);
    }*/

}

