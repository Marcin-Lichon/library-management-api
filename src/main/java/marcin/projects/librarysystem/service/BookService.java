package marcin.projects.librarysystem.service;

import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.mapper.BookMapper;
import marcin.projects.librarysystem.model.Book;
import marcin.projects.librarysystem.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public List<BookResponseDto> getAllBooks(String author){
        List<Book> books;

        if(author != null && !author.isBlank()){
            books = bookRepository.findBookByAuthor(author);
        } else {
            books = bookRepository.findAll();
        }

        return bookMapper.toDtoList(books);
    }

    public Optional<BookResponseDto> getBookById(Long id){
        return bookRepository.findById(id)
                .map(bookMapper::toDto);

    }

    public boolean deleteBookById(Long id){
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    @Transactional
    public Optional<BookResponseDto> updateById(Long id, BookRequestDto dto){
        return bookRepository.findById(id)
                .map( existingBook -> {
                    existingBook.setId(id);
                    existingBook.setTitle(dto.title());
                    existingBook.setAuthor(dto.author());
                    existingBook.setReleaseYear(dto.releaseYear());
                    existingBook.setIsbn(dto.isbn());

                    var updatedBook = bookRepository.save(existingBook);
                    return bookMapper.toDto(updatedBook);
                });
    }


}
