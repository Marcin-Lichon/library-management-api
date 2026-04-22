package marcin.projects.librarysystem.dto;


import java.util.List;

/**
 * DTO for {@link marcin.projects.librarysystem.model.Author}
 */
public record AuthorResponseDto(
        Long id,
        String firstName,
        String lastName,
        int age,
        List<AuthorBookDto> books
){}