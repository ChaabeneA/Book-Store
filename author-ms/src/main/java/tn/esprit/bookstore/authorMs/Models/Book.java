package tn.esprit.bookstore.authorMs.Models;

import lombok.*;
import tn.esprit.bookstore.authorMs.Entity.Author;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    private String id;
    private String isbn;
    private String title;
    private Double price;
    private String stock;
    private Long authorId;
    private Author author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}