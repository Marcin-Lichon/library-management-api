package marcin.projects.librarysystem.controller;

import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.AuthorRequestDto;
import marcin.projects.librarysystem.dto.AuthorResponseDto;
import marcin.projects.librarysystem.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponseDto> saveAuthor(@Validated @RequestBody AuthorRequestDto dto){
        AuthorResponseDto savedAuthor = authorService.saveAuthor(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAuthor);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorResponseDto>> showAllAuthorsOrById(
            @RequestParam (required = false) Long id){
        return ResponseEntity.ok(authorService.getAuthors(id));
    }


}
