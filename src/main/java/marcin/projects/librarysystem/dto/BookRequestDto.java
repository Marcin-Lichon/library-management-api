package marcin.projects.librarysystem.dto;

import java.io.Serializable;

/**
 * DTO for {@link marcin.projects.librarysystem.model.Book}
 */
public record BookRequestDto(String title, String author, int releaseYear, String isbn) implements Serializable {
}