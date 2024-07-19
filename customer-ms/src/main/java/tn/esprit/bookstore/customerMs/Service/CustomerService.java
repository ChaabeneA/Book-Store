package tn.esprit.bookstore.customerMs.Service;



import tn.esprit.bookstore.customerMs.Entity.Customer;
import tn.esprit.bookstore.customerMs.Models.Order;

import java.util.List;

public interface CustomerService {
    Customer getCustomerById(Long id);
    List<Customer> getAllCustomers();
    List<Order>  getOrdersByCustomerId(String id);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
    Order addOrder(Long id, Order order);

}
