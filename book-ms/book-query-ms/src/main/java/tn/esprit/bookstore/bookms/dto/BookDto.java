package tn.esprit.bookstore.bookms.dto;




import tn.esprit.bookstore.bookms.bookqueryms.Entity.Book;
import tn.esprit.bookstore.bookms.bookqueryms.Models.Author;

import java.time.LocalDateTime;

public record BookDto(String id,String isbn,String title, Double price, String stock, String authorId , Author author, LocalDateTime createdAt, LocalDateTime updatedAt) {
    public static Book mapToBook(BookDto bookDto) {
        return new Book(bookDto.id(), bookDto.title(),bookDto.isbn(),bookDto.price(), bookDto.stock(), bookDto.authorId(),bookDto.author(), bookDto.createdAt(), bookDto.updatedAt());
    }

    public static BookDto mapToBookDto(Book book) {
        return new BookDto(book.getId(), book.getIsbn(),book.getTitle(), book.getPrice(), book.getStock(),book.getAuthorId(),book.getAuthor() , book.getCreatedAt(), book.getUpdatedAt());
    }
}