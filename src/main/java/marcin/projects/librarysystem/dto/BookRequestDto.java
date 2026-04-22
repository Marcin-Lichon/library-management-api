package marcin.projects.librarysystem.dto;

import jakarta.validation.constraints.*;

/**
 * DTO for {@link marcin.projects.librarysystem.model.Book}
 */
public record BookRequestDto(
        @NotBlank(message = "Title cannot be blank")
        @Size(min = 2, max=50, message = "Title must be between 2 and 50 characters")
        String title,

        @NotNull(message = "Author ID is required")
        Long authorId,

        @Max(value = 2026, message = "Release date cannot be from future")
        int releaseYear,

        @Pattern(regexp = "^(97[89])?\\d{9}(\\d|X)$", message = "Incorrect ISBN format")
        String isbn
        ){}