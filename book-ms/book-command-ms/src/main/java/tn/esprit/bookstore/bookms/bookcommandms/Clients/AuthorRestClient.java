package tn.esprit.bookstore.bookms.bookcommandms.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.bookms.bookcommandms.Models.Author;

import java.util.List;
@FeignClient(name = "author-ms")
public interface AuthorRestClient {
    @GetMapping("authors/{id}")
    Author findAuthorById(@PathVariable String id);
    @GetMapping("authors/{id}/validate")
    Boolean validateAuthor(@PathVariable Long id);
    @DeleteMapping("authors/{id}")
    void deleteAuthor(@PathVariable Long id);
    @PostMapping("authors")
    Author createAuthor(@RequestBody Author author);
    @GetMapping("authors/{fName}/{lName}")
    Author findAuthorByName(@PathVariable String fName,@PathVariable String lName);

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
