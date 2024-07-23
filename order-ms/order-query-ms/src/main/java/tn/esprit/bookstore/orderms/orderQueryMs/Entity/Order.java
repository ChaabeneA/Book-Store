package tn.esprit.bookstore.orderms.orderQueryMs.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.bookstore.orderms.orderQueryMs.Models.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "book_orders")
public class Order {
    @Id
    private String id;
    private String price;
    private String address;
    private String customerId;
    @Transient
    private Customer customer;
    private String quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
