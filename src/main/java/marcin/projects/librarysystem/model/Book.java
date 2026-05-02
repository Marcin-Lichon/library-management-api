package marcin.projects.librarysystem.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import marcin.projects.librarysystem.model.enums.BookStatus;

@Entity
@Getter
@Setter
public class Book {
    
    public Book(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    private int releaseYear;
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status = BookStatus.AVAILABLE;
}
