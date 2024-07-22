package tn.esprit.bookstore.orderCommandMs.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import tn.esprit.bookstore.orderCommandMs.Models.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "orders")
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

