package tn.esprit.bookstore.bookms.bookqueryms.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.bookms.bookqueryms.Entity.Book;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends CrudRepository<Book,String> {
    List<Book> findAllByAuthorId(String id);
    Page<Book> findAll(Pageable pageable);
}