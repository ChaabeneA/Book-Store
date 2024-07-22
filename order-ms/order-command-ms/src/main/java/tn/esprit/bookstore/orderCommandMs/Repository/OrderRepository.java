package tn.esprit.bookstore.orderCommandMs.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.orderCommandMs.Entity.Order;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends MongoRepository<Order,String> {
    List<Order> findAllByCustomerId(String id);
}