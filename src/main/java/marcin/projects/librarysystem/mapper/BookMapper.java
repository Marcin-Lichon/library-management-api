package marcin.projects.librarysystem.mapper;

import marcin.projects.librarysystem.dto.BookRequestDto;
import marcin.projects.librarysystem.dto.BookResponseDto;
import marcin.projects.librarysystem.model.Book;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDto dto){
        if (dto == null){
            return null;
        }

        Book book = new Book();
        book.setTitle(dto.title());
        book.setAuthor(dto.author());
        book.setReleaseYear(dto.releaseYear());
        book.setIsbn(dto.isbn());

        return book;
    }

    public BookResponseDto toDto(Book book){
        if(book==null){
            return null;
        }
        return new BookResponseDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getReleaseYear(),
                book.getIsbn()
        );
    }

    public List<BookResponseDto> toDtoList(List<Book> books){
        if(books.isEmpty()){
            return Collections.emptyList();
        }
        return books.stream()
                .map(this::toDto)
                .toList();
    }


}
