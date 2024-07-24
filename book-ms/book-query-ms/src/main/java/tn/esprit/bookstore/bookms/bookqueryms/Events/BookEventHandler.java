package tn.esprit.bookstore.bookms.bookqueryms.Events;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.bookstore.bookms.bookqueryms.Service.BookQueryService;
import tn.esprit.bookstore.bookms.dto.BookDto;

@Service
@RequiredArgsConstructor
public class BookEventHandler {
    private final BookQueryService bookQueryService;

    public void handleBookCreatedEvent(BookDto bookDto) {
        bookQueryService.add(BookDto.mapToBook(bookDto));
    }

    public void handleBookUpdatedEvent(BookDto bookDto) {
        bookQueryService.update(BookDto.mapToBook(bookDto));
    }

    public void handleBookDeletedEvent(String idBook) {
        bookQueryService.delete(idBook);
    }
}