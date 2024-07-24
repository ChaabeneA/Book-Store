package tn.esprit.bookstore.bookms.bookqueryms.Clients;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.bookstore.bookms.bookqueryms.Models.Author;

import java.util.List;

@FeignClient(name = "author-ms")
public interface AuthorRestClient {
    @GetMapping("authors/{id}")
    @CircuitBreaker(name = "author-ms",fallbackMethod = "getDefaultAuthor")
    Author findAuthorById(@PathVariable String id);

    @GetMapping("authors/all")
    List<Author> allAuthors();
    default Author getDefaultAuthor(String id,Exception exception)
    {
        Author author=new Author();
        author.setId(id);
        author.setFirstName("not available");
        author.setLastName("not available");
        author.setBio("not available");
        return author;
    }
}
