package marcin.projects.librarysystem.controller;

import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponseDto> save(@Validated @RequestBody BookRequestDto dto){
        BookResponseDto savedBook = bookService.addBook(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getAllOrById(
            @RequestParam(required = false) Long authorId){

        var bookList = bookService.getBooks(authorId);
        return ResponseEntity.ok(bookList);
    }


    @DeleteMapping({"{id}"})
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        if(bookService.deleteBookById(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping({"{id}"})
    public ResponseEntity<BookResponseDto> updateById(@PathVariable Long id, @Validated @RequestBody BookRequestDto dto){
        return ResponseEntity.of(bookService.updateById(id, dto));
    }


}
