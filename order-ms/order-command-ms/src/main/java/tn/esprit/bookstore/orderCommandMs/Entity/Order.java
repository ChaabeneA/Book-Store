package tn.esprit.bookstore.orderCommandMs.Entity;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.bookstore.orderCommandMs.Models.Customer;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String price;
    private String address;
    private String customerId;
    @Transient
    private Customer customer;
    private String quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

