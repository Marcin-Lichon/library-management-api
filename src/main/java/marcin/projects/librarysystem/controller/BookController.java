package marcin.projects.librarysystem.controller;

import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
