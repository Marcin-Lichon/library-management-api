package marcin.projects.librarysystem.dto;

import java.io.Serializable;

public record BookResponseDto(Long id, String title, String author, int releaseYear, String isbn) {
}
