package tn.esprit.bookstore.orderms.orderQueryMs.Events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.dto.OrderDto;
import tn.esprit.bookstore.orderms.orderQueryMs.Service.OrderQueryService;

@Service
@RequiredArgsConstructor
public class OrderEventHandler {
    private final OrderQueryService orderQueryService;

    public void handleOrderCreatedEvent(OrderDto orderDto) {
        orderQueryService.add(OrderDto.mapToOrder(orderDto));
    }

    public void handleOrderUpdatedEvent(OrderDto orderDto) {
        orderQueryService.update(OrderDto.mapToOrder(orderDto));
    }

    public void handleOrderDeletedEvent(long idOrder) {
        orderQueryService.delete(idOrder);
    }
}