package tn.esprit.bookstore.orderms.orderQueryMs.Entity;


import jakarta.persistence.*;
import lombok.*;
import tn.esprit.bookstore.orderms.orderQueryMs.Models.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String price;
    private String address;
    private String customerId;
    @Transient
    private Customer customer;
    private String quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
