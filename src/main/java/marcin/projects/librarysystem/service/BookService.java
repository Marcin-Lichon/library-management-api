package marcin.projects.librarysystem.service;

import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.mapper.BookMapper;
import marcin.projects.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookResponseDto addBook(BookRequestDto dto){
        var book = bookMapper.toEntity(dto);
        var savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

}
