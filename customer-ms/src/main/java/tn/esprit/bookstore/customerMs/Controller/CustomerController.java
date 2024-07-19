package tn.esprit.bookstore.customerMs.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.customerMs.Entity.Customer;
import tn.esprit.bookstore.customerMs.Models.Order;
import tn.esprit.bookstore.customerMs.Service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("customers/")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Long id)
    {
        return customerService.getCustomerById(id);
    }

    @GetMapping
    public List<Customer> getAllCustomers()
    {
        return customerService.getAllCustomers();
    }



    @GetMapping("orders/{id}")
    public List<Order> getOrdersByCustomerId(@PathVariable String id)
    {
        return customerService.getOrdersByCustomerId(id);
    }




    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer)
    {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer)
    {
        customer.setId(id);
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id)
    {
        customerService.deleteCustomer(id);
    }
    @PostMapping("addOrder/{id}")
    public Order makeOrder(@PathVariable Long id, @RequestBody Order order)
    {
        return customerService.addOrder(id,order);
    }

}