package marcin.projects.librarysystem.dto;

public record AuthorBookDto(
        long id,
        String title,
        int releaseYear,
        String isbn
) {}