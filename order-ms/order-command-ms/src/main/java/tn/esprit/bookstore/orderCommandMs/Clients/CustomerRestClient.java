package tn.esprit.bookstore.orderCommandMs.Clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.orderCommandMs.Models.Customer;

import java.util.List;
@FeignClient(name = "customer-ms")
public interface CustomerRestClient {
    @GetMapping("customers/{id}")
    Customer findCustomerById(@PathVariable String id);
    @GetMapping("customers/{id}/validate")
    Boolean validateCustomer(@PathVariable Long id);
    @DeleteMapping("customers/{id}")
    void deleteCustomer(@PathVariable Long id);
    @PostMapping("customers")
    Customer createCustomer(@RequestBody Customer customer);
    @GetMapping("customers/email/{email}")
    Customer findCustomerByEmail(@PathVariable String email);

    @GetMapping("customers/all")
    List<Customer> allCustomers();
    default Customer getDefaultCustomer(String id,Exception exception)
    {
        Customer customer=new Customer();
        customer.setId(id);
        customer.setFirstName("not available");
        customer.setLastName("not available");
        customer.setEmail("not available");
        return customer;
    }
}
