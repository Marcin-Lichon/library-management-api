package marcin.projects.librarysystem.dto;

import marcin.projects.librarysystem.model.enums.BookStatus;

public record AuthorBookDto(
        long id,
        String title,
        int releaseYear,
        String isbn,
        BookStatus status
) {}