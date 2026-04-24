package marcin.projects.librarysystem.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.mapper.BookMapper;
import marcin.projects.librarysystem.model.Author;
import marcin.projects.librarysystem.model.Book;
import marcin.projects.librarysystem.repository.AuthorRepository;
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
    private final AuthorRepository authorRepository;

    public BookResponseDto addBook(BookRequestDto dto){
        var author = authorRepository.findById(dto.authorId())
                .orElseThrow( ()-> new EntityNotFoundException("Author not found with id: " + dto.authorId()));

        var book = bookMapper.toEntity(dto, author);
        var savedBook = bookRepository.save(book);
        return bookMapper.toDto(savedBook);
    }

    public List<BookResponseDto> getBooks(Long authorId){
        List<Book> books;

        if(authorId != null){
            books = bookRepository.findByAuthorId(authorId);
        } else {
            books = bookRepository.findAll();
        }

        return bookMapper.toDtoList(books);
    }

    @Transactional
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

                    Author author = authorRepository.findById(dto.authorId())
                                    .orElseThrow( ()-> new EntityNotFoundException("Author not found with id:"+dto.authorId()));

                    existingBook.setTitle(dto.title());
                    existingBook.setAuthor(author);
                    existingBook.setReleaseYear(dto.releaseYear());
                    existingBook.setIsbn(dto.isbn());

                    var updatedBook = bookRepository.save(existingBook);
                    return bookMapper.toDto(updatedBook);
                });
    }


}
