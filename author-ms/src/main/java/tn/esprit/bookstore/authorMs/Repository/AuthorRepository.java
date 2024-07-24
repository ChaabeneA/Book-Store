package tn.esprit.bookstore.authorMs.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.bookstore.authorMs.Entity.Author;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByFirstNameAndLastName(String fName, String lName);
}