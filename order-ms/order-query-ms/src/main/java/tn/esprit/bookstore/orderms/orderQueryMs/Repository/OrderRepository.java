package tn.esprit.bookstore.orderms.orderQueryMs.Repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findAllByCustomerId(String id);
}