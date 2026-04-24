package marcin.projects.librarysystem.dto;


public record BookResponseDto(
        Long id,
        String title,
        AuthorResponseShortDto author,
        int releaseYear,
        String isbn
        ){}
