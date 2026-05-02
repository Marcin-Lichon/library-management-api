package marcin.projects.librarysystem.service;

import jakarta.persistence.EntityNotFoundException;
import marcin.projects.librarysystem.dto.AuthorResponseShortDto;
import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.mapper.BookMapper;
import marcin.projects.librarysystem.model.Author;
import marcin.projects.librarysystem.model.Book;
import marcin.projects.librarysystem.model.enums.BookStatus;
import marcin.projects.librarysystem.repository.AuthorRepository;
import marcin.projects.librarysystem.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    void shouldAddNewBook_WhenAuthorExists(){
        //given
        Long authorId = 1L;
        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);

        BookRequestDto requestDto = new BookRequestDto("Title",authorId,2005,"97809780316029186");


        Book bookToSave = new Book();
        bookToSave.setId(100L);
        bookToSave.setTitle("Title");
        bookToSave.setAuthor(existingAuthor);

        Book savedBook = new Book();
        savedBook.setId(100L);
        savedBook.setTitle("Title");
        savedBook.setAuthor(existingAuthor);
        savedBook.setStatus(BookStatus.AVAILABLE);

        BookResponseDto expectedResponse = new BookResponseDto(
                100L,"Title",new AuthorResponseShortDto("firstName", "lastName", 2),2005,"97809780316029186", BookStatus.AVAILABLE
        );

        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthor));
        when(bookMapper.toEntity(requestDto, existingAuthor)).thenReturn(bookToSave);
        when(bookRepository.save(bookToSave)).thenReturn(savedBook);
        when(bookMapper.toDto(savedBook)).thenReturn(expectedResponse);

        //when
        BookResponseDto result = bookService.addBook(requestDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.title()).isEqualTo("Title");
        assertThat(result.status()).isEqualTo(BookStatus.AVAILABLE);

        verify(bookRepository, times(1)).save(bookToSave);
    }

    @Test
    void shouldThrowException_WhenAuthorDoesNotExist(){
        //given
        Long wrongAuthorId = 1L;

        BookRequestDto requestDto = new BookRequestDto("Title",wrongAuthorId,2005,"97809780316029186");

        //when
        when(authorRepository.findById(wrongAuthorId)).thenReturn(Optional.empty());

        //then
        assertThrows(EntityNotFoundException.class,
                () -> bookService.addBook(requestDto));

        verify(bookRepository, never()).save(any());
        verify(bookMapper, never()).toEntity(any(), any());


    }

    @Test
    void UpdateById_ShouldUpdate_WhenIdExists(){
        //given
        Long bookId = 1L;
        Long authorId = 2L;
        Author existingAuthor = new Author();
        existingAuthor.setId(authorId);

        BookRequestDto requestDto = new BookRequestDto("Witcher",authorId,2005,"97809780316029186");

        Book existingBook = new Book();
        existingBook.setId(bookId);
        existingBook.setTitle("Title");
        existingBook.setAuthor(existingAuthor);
        existingBook.setReleaseYear(2000);
        existingBook.setIsbn("97809780316029186");
        existingBook.setStatus(BookStatus.AVAILABLE);

        Book updatedBook = new Book();
        updatedBook.setId(bookId);
        updatedBook.setTitle("Witcher");
        updatedBook.setAuthor(existingAuthor);
        updatedBook.setReleaseYear(2005);
        updatedBook.setIsbn("97809780316029186");
        existingBook.setStatus(BookStatus.BORROWED);


        BookResponseDto expectedResponse = new BookResponseDto(
                bookId,"Witcher",new AuthorResponseShortDto("firstName", "lastName", 20),2005,"97809780316029186",BookStatus.BORROWED);


        when(bookRepository.findById(bookId)).thenReturn(Optional.of(existingBook));
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(existingAuthor));
        when(bookRepository.save(existingBook)).thenReturn(updatedBook);
        when(bookMapper.toDto(updatedBook)).thenReturn(expectedResponse);

        //when
        Optional<BookResponseDto> result = bookService.updateById(bookId, requestDto);

        //then
        assertTrue(result.isPresent());
        assertEquals("Witcher", result.get().title());
        assertEquals(2005, result.get().releaseYear());
        assertEquals(BookStatus.BORROWED,result.get().status());

        verify(bookRepository).findById(bookId);
        verify(authorRepository).findById(authorId);
        verify(bookRepository, times(1)).save(existingBook);
        verify(bookMapper, times(1)).toDto(updatedBook);
    }


}
