package tn.esprit.bookstore.bookms.bookqueryms.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.bookms.bookqueryms.Clients.AuthorRestClient;
import tn.esprit.bookstore.bookms.bookqueryms.Entity.Book;
import tn.esprit.bookstore.bookms.bookqueryms.Repository.BookRepository;
import tn.esprit.bookstore.bookms.dto.BookDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService {
    private final BookRepository bookRepository;

    private final AuthorRestClient authorRestClient;

        @Override
    public Book add(Book book) {
        book.setCreatedAt(LocalDateTime.now());
        return bookRepository.save(book);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean delete(String idBook) {
        bookRepository.deleteById(idBook);
        return bookRepository.existsById(idBook);
    }

    @Override
    public Page<BookDto> getBooks(int pageNbr, int pageSize) {
        return bookRepository.findAll(PageRequest.of(pageNbr,pageSize)).map(BookDto::mapToBookDto);
    }

    @Override
    public BookDto getBook(String id) {
        Book book=  bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("book not found"));
        return BookDto.mapToBookDto(book);
    }
    @Override
    public List<BookDto> getBooksByAuthorId(String id) {
        List<Book> books = bookRepository.findAllByAuthorId(id);
        return books.stream()
                .map(BookDto::mapToBookDto)
                .collect(Collectors.toList());
    }


   /* @Override
    public BookDto getBookByName(String name) {
        Book book= bookRepository.findByName(name)
                .orElseThrow(() ->new IllegalArgumentException("book not found with this name"));
        return BookDto.mapToBookDto(book);
    }*/
}

