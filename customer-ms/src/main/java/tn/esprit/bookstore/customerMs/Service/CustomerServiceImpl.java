package tn.esprit.bookstore.customerMs.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.customerMs.Clients.OrderCommandClient;
import tn.esprit.bookstore.customerMs.Clients.OrderQueryClient;
import tn.esprit.bookstore.customerMs.Entity.Customer;
import tn.esprit.bookstore.customerMs.Models.Order;
import tn.esprit.bookstore.customerMs.Repository.CustomerRepository;
import tn.esprit.bookstore.customerMs.Utility.ConversionUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final OrderQueryClient orderQueryClient;
    private final OrderCommandClient orderCommandClient;
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public List<Order>  getOrdersByCustomerId(String id) {
        return orderQueryClient.allOrdersByCustomerId(id);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
    @Override
    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    @Override
    public Order addOrder(Long id, Order order) {
        Customer customer=customerRepository.findById(id).orElse(null);
        order.setCustomer(customer);
        order.setCustomerId(id);
        return orderCommandClient.AddOrder(order);
    }
    @Override
    public Boolean validateCustomer(Long customerId) {
        return customerRepository.existsById(customerId);
    }
    @Override
    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
    @Override
    public List<Order> getOrdersByCustomerId(Long customerId) {
        String id= ConversionUtil.longToString(customerId);
        return orderQueryClient.allOrdersByCustomerId(id);
    }
}
