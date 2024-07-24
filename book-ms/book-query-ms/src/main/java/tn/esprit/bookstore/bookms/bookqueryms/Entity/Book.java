package tn.esprit.bookstore.bookms.bookqueryms.Entity;


import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;
import tn.esprit.bookstore.bookms.bookqueryms.Models.Author;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("Book")
public class Book {
    @Id
    private String id;
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
