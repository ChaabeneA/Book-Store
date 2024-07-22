package tn.esprit.bookstore.orderms.orderQueryMs.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.orderms.orderQueryMs.Entity.Order;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByCustomerId(String id);
    //Optional<Order> findByName(String name);
}