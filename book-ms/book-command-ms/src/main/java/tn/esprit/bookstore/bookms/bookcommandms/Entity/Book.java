package tn.esprit.bookstore.bookms.bookcommandms.Entity;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.bookstore.bookms.bookcommandms.Models.Author;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private Double price;
    private String stock;
    private String authorId;
    @Transient
    private Author author;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

