package marcin.projects.librarysystem.dto;


/**
 * DTO for {@link marcin.projects.librarysystem.model.Author}
 */
public record AuthorResponseShortDto(
        String firstName,
        String lastName,
        int age
) {}