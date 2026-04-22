package marcin.projects.librarysystem.mapper;

import marcin.projects.librarysystem.dto.AuthorBookDto;
import marcin.projects.librarysystem.dto.AuthorRequestDto;
import marcin.projects.librarysystem.dto.AuthorResponseDto;
import marcin.projects.librarysystem.model.Author;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequestDto author){
        if(author == null){
            return null;
        }

        Author authorEntity = new Author();
        authorEntity.setFirstName(author.firstName());
        authorEntity.setLastName(author.lastName());
        authorEntity.setAge(author.age());

        return authorEntity;
    }

    public AuthorResponseDto toDto(Author author){
        if(author == null){
            return null;
        }

        List<AuthorBookDto> authorsBooks = author.getBooks().stream()
                .map(book -> new AuthorBookDto(
                        book.getId(),
                        book.getTitle(),
                        book.getReleaseYear(),
                        book.getIsbn()
                ))
                .toList();

        return new AuthorResponseDto(
                author.getId(),
                author.getFirstName(),
                author.getLastName(),
                author.getAge(),
                authorsBooks
        );

    }
}
