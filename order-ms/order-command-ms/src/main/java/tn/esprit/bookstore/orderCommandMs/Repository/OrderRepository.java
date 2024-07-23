package tn.esprit.bookstore.orderCommandMs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.orderCommandMs.Entity.Order;

import java.util.List;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByCustomerId(String id);
}