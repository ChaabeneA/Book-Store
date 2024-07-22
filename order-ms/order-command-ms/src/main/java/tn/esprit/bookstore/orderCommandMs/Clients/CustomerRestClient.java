package tn.esprit.bookstore.orderCommandMs.Clients;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.bookstore.orderCommandMs.Models.Customer;

import java.util.List;
@FeignClient(name = "Customer-Microservice")
public interface CustomerRestClient {
    @GetMapping("customers/{id}")
    @CircuitBreaker(name = "Customer-Microservice",fallbackMethod = "getDefaultCustomer")
    Customer findCustomerById(@PathVariable String id);

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
