package tn.esprit.bookstore.authorMs.Service;



import tn.esprit.bookstore.authorMs.Entity.Author;
import tn.esprit.bookstore.authorMs.Models.Book;

import java.util.List;

public interface AuthorService {
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
    List<Book>  getBooksByAuthorId(String id);
    Author createAuthor(Author author);
    Author updateAuthor(Author author);
    void deleteAuthor(Long id);
    Book addBook(Long id, Book book);
    Boolean validateAuthor(Long authorId);
    Author findAuthorByName(String fName,String lName);
    List<Book> getBooksByAuthorId(Long authorId);
}
