package tn.esprit.bookstore.bookms.bookqueryms.Service;


import org.springframework.data.domain.Page;
import tn.esprit.bookstore.bookms.bookqueryms.Entity.Book;
import tn.esprit.bookstore.bookms.dto.BookDto;

import java.util.List;


public interface BookQueryService {
    Book add(Book book);
    Book update(Book book);
    boolean delete(String idBook);


    Page<BookDto> getBooks(int pageNbr, int pageSize);

    BookDto getBook(String id);

    List<BookDto> getBooksByAuthorId(String id);

}
