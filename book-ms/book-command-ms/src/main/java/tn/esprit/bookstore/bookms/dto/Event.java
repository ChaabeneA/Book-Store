package tn.esprit.bookstore.bookms.dto;

import java.time.LocalDateTime;

public record Event(EventType type, BookDto bookDto, LocalDateTime eventCreatedAt){}