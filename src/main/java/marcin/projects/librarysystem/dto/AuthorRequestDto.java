package marcin.projects.librarysystem.dto;

import marcin.projects.librarysystem.model.Book;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link marcin.projects.librarysystem.model.Author}
 */
public record AuthorRequestDto(
        String firstName,
        String lastName,
        int age
) {}