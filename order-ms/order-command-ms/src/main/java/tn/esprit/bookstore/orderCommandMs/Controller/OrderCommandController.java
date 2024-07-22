package tn.esprit.bookstore.orderCommandMs.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderCommandMs.Service.OrderCommandService;

import java.util.Map;

@RestController
@RequestMapping("/orders/commands")
public class OrderCommandController {
    @Autowired
    private OrderCommandService orderCommandService;

    @PostMapping
    public OrderDto add(@RequestBody OrderDto orderDto) {
        return orderCommandService.add(orderDto);
    }

    @PatchMapping("{id}")
    public OrderDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable String id){
        return orderCommandService.update(id,fields);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable String id){
        return orderCommandService.delete(id);
    }
}
