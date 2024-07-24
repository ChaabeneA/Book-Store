package tn.esprit.bookstore.bookms.bookcommandms.Service;


import tn.esprit.bookstore.bookms.dto.BookDto;

import java.util.List;
import java.util.Map;

public interface BookCommandService {

    BookDto add(BookDto bookDto);

    List<BookDto> addBooksToAuthor(String authorId, List<BookDto> bookDtos);

    BookDto update(Long idBook, Map<Object,Object> fields,String authorId);

    boolean delete(Long idBook);
}