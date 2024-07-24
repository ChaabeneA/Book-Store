package tn.esprit.bookstore.bookms.bookcommandms.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tn.esprit.bookstore.bookms.bookcommandms.Entity.Book;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findAllByAuthorId(String id);
}