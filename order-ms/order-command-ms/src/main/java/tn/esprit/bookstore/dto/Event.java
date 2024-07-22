package tn.esprit.bookstore.dto;

import java.time.LocalDateTime;

public record Event(EventType type, OrderDto customerDto, LocalDateTime eventCreatedAt){}