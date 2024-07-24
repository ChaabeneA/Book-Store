package tn.esprit.bookstore.bookms.bookcommandms.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import tn.esprit.bookstore.bookms.bookcommandms.Clients.AuthorRestClient;
import tn.esprit.bookstore.bookms.bookcommandms.Entity.Book;
import tn.esprit.bookstore.bookms.bookcommandms.Events.KafkaProducer;
import tn.esprit.bookstore.bookms.bookcommandms.Models.Author;
import tn.esprit.bookstore.bookms.bookcommandms.Repository.BookRepository;
import tn.esprit.bookstore.bookms.bookcommandms.Utility.ConversionUtil;
import tn.esprit.bookstore.bookms.dto.BookDto;
import tn.esprit.bookstore.bookms.dto.Event;
import tn.esprit.bookstore.bookms.dto.EventType;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookCommandServiceImpl implements BookCommandService {

    private final BookRepository bookRepository;

    private final KafkaProducer kafkaProducer;
    private final AuthorRestClient authorRestClient;

    @Override
    public BookDto add(BookDto bookDto) {
        Boolean isAuthorValid = false;
        if (bookDto.authorId() != null) {
            Long id = ConversionUtil.stringToLong(bookDto.authorId());
            isAuthorValid = authorRestClient.validateAuthor(id);
        } else {
            isAuthorValid = false;
        }

        if (isAuthorValid) {
            // Existing author, proceed with book creation
            Book book = BookDto.mapToBook(bookDto);
            Author author=authorRestClient.findAuthorById(book.getAuthorId());
            book.setAuthor(author);
            book.setCreatedAt(LocalDateTime.now());
            bookDto = BookDto.mapToBookDto(bookRepository.save(book));
            kafkaProducer.produceEvent(new Event(EventType.CREATED_BOOK_EVENT, bookDto, LocalDateTime.now()));
            return bookDto;
        } else {
            // Author does not exist, check if new author details are present
            if (bookDto.author() != null) {
                Author newAuthor = bookDto.author();
                authorRestClient.createAuthor(newAuthor);
                String id=authorRestClient.findAuthorByName(newAuthor.getFirstName(),newAuthor.getLastName()).getId();
                Book book = BookDto.mapToBook(bookDto);
                book.setAuthorId(id);
                book.setCreatedAt(LocalDateTime.now());
                bookDto = BookDto.mapToBookDto(bookRepository.save(book));
                kafkaProducer.produceEvent(new Event(EventType.CREATED_BOOK_EVENT, bookDto, LocalDateTime.now()));
                return bookDto;
            }
            return null;
        }
    }
    @Override
    public List<BookDto> addBooksToAuthor(String authorId, List<BookDto> bookDtos) {
        // Validate the author exists
        Long id = ConversionUtil.stringToLong(authorId);
        boolean isAuthorValid = authorRestClient.validateAuthor(id);

        if (!isAuthorValid) {
            throw new IllegalArgumentException("Invalid author ID: " + authorId);
        }

        // Convert BookDtos to Book entities and set the author ID and timestamps
        List<Book> books = bookDtos.stream()
                .map(bookDto -> {
                    Book book = BookDto.mapToBook(bookDto);
                    book.setAuthorId(authorId);
                    book.setCreatedAt(LocalDateTime.now());
                    return book;
                })
                .collect(Collectors.toList());

        // Save the books to the repository
        List<Book> savedBooks = bookRepository.saveAll(books);

        // Convert saved books back to DTOs and produce Kafka events
        List<BookDto> savedBookDtos = savedBooks.stream()
                .map(BookDto::mapToBookDto)
                .collect(Collectors.toList());

        savedBookDtos.forEach(bookDto ->
                kafkaProducer.produceEvent(new Event(EventType.CREATED_BOOK_EVENT, bookDto, LocalDateTime.now()))
        );

        return savedBookDtos;
    }
    @Override
    public BookDto update(Long idBook, Map<Object, Object> fields,String authorId) {
        Long id= ConversionUtil.stringToLong(authorId);
        Boolean isAuthorValid = authorRestClient.validateAuthor(id);
        if (!isAuthorValid) {
            throw new IllegalArgumentException("Invalid author ID: " + authorId);
        }

        // Find the book
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + idBook));

        // Update fields in the book
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Book.class, (String) key);
            field.setAccessible(true);

            if (field.getType().equals(LocalDate.class)) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
                LocalDate localDate = LocalDate.parse((String) value, formatter);
                ReflectionUtils.setField(field, book, localDate);
            } else {
                ReflectionUtils.setField(field, book, value);
            }
        });

        // Update the author ID
        book.setAuthorId(authorId);

        // Set the updatedAt timestamp
        book.setUpdatedAt(LocalDateTime.now());

        // Save the updated book
        BookDto bookDto = BookDto.mapToBookDto(bookRepository.save(book));

        // Produce the event to Kafka
        kafkaProducer.produceEvent(new Event(EventType.UPDATED_BOOK_EVENT, bookDto, LocalDateTime.now()));
        List<Book> authorBooks = bookRepository.findAllByAuthorId(authorId);
        if (authorBooks.isEmpty()) {
            // Delete the author if no other books exist
            authorRestClient.deleteAuthor(ConversionUtil.stringToLong(authorId));
        }
        return bookDto;
    }

    @Override
    public boolean delete(Long idBook) {
        // Find the book and map to DTO
        Book book = bookRepository.findById(idBook)
                .orElseThrow(() -> new IllegalArgumentException("Book not found: " + idBook));
        BookDto bookDto = BookDto.mapToBookDto(book);

        // Get the author ID associated with the book
        String authorId = book.getAuthorId();

        // Delete the book
        bookRepository.deleteById(idBook);

        // Produce the deleted book event
        kafkaProducer.produceEvent(new Event(EventType.DELETED_BOOK_EVENT, bookDto, LocalDateTime.now()));

        // Check if the author has any other books
        List<Book> authorBooks = bookRepository.findAllByAuthorId(authorId);
        if (authorBooks.isEmpty()) {
            // Delete the author if no other books exist
            authorRestClient.deleteAuthor(ConversionUtil.stringToLong(authorId));
        }

        // Return whether the book still exists
        return bookRepository.existsById(idBook);
    }



}