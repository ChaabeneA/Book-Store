package tn.esprit.bookstore.authorMs.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.bookstore.authorMs.Models.Book;

import java.util.List;

@FeignClient(name = "book-query-ms")
public interface BookQueryClient {
    @GetMapping("books/queries/customer/{id}")
    List<Book> allBooksByAuthorId(@PathVariable String id);

}
