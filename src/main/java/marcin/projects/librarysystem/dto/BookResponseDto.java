package marcin.projects.librarysystem.dto;


import marcin.projects.librarysystem.model.enums.BookStatus;

public record BookResponseDto(
        Long id,
        String title,
        AuthorResponseShortDto author,
        int releaseYear,
        String isbn,
        BookStatus status
        ){}
