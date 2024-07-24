package tn.esprit.bookstore.bookms.bookcommandms.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.bookstore.bookms.bookcommandms.Service.BookCommandService;
import tn.esprit.bookstore.bookms.dto.BookDto;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books/commands")
@RequiredArgsConstructor
public class BookCommandController {

    private final BookCommandService bookCommandService;

    @PostMapping
    public BookDto add(@RequestBody BookDto bookDto) {
        return bookCommandService.add(bookDto);
    }

    @PostMapping("/author/{authorId}")
    public ResponseEntity<List<BookDto>> addBooksToAuthor(@PathVariable String authorId, @RequestBody List<BookDto> bookDtos) {
        List<BookDto> savedBooks = bookCommandService.addBooksToAuthor(authorId, bookDtos);
        return ResponseEntity.ok(savedBooks);
    }

    @PatchMapping("{id}/{authorId}")
    public BookDto patchUpdate(@RequestBody Map<Object,Object> fields, @PathVariable Long id,@PathVariable String authorId){
        return bookCommandService.update(id,fields,authorId);
    }

    @DeleteMapping("{id}")
    public boolean delete( @PathVariable Long id){
        return bookCommandService.delete(id);
    }
}
