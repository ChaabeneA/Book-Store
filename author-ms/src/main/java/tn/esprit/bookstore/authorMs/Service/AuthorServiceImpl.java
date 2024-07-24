package tn.esprit.bookstore.authorMs.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.authorMs.Clients.BookCommandClient;
import tn.esprit.bookstore.authorMs.Clients.BookQueryClient;
import tn.esprit.bookstore.authorMs.Entity.Author;
import tn.esprit.bookstore.authorMs.Models.Book;
import tn.esprit.bookstore.authorMs.Repository.AuthorRepository;
import tn.esprit.bookstore.authorMs.Utility.ConversionUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final BookQueryClient bookQueryClient;
    private final BookCommandClient bookCommandClient;
    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Book>  getBooksByAuthorId(String id) {
        return bookQueryClient.allBooksByAuthorId(id);
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    @Override
    public Book addBook(Long id, Book book) {
        Author author=authorRepository.findById(id).orElse(null);
        book.setAuthor(author);
        book.setAuthorId(id);
        return bookCommandClient.AddBook(book);
    }
    @Override
    public Boolean validateAuthor(Long authorId) {
        return authorRepository.existsById(authorId);
    }
    @Override
    public Author findAuthorByName(String fName,String lName) {
        return authorRepository.findByFirstNameAndLastName(fName,lName).orElse(null);
    }
    @Override
    public List<Book> getBooksByAuthorId(Long authorId) {
        String id= ConversionUtil.longToString(authorId);
        return bookQueryClient.allBooksByAuthorId(id);
    }
}
