package tn.esprit.bookstore.orderCommandMs.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderCommandMs.Service.OrderCommandService;

import java.util.Map;

@RestController
@RequestMapping("/orders/commands")
@RequiredArgsConstructor
public class OrderCommandController {

    private final OrderCommandService orderCommandService;

    @PostMapping
    public OrderDto add(@RequestBody OrderDto orderDto) {
        return orderCommandService.add(orderDto);
    }

    @PatchMapping("{id}/{customerId}")
    public OrderDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable Long id,@PathVariable String customerId){
        return orderCommandService.update(id,fields,customerId);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable Long id){
        return orderCommandService.delete(id);
    }
}
