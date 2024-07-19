package tn.esprit.bookstore.customerMs.Models;

import lombok.*;
import tn.esprit.bookstore.customerMs.Entity.Customer;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private String id;
    private String price;
    private String address;
    private Long customerId;
    private Customer customer;
    private String quantity;
}