package marcin.projects.librarysystem.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import marcin.projects.librarysystem.dto.AuthorRequestDto;
import marcin.projects.librarysystem.dto.AuthorResponseDto;
import marcin.projects.librarysystem.mapper.AuthorMapper;
import marcin.projects.librarysystem.model.Author;
import marcin.projects.librarysystem.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorResponseDto saveAuthor(AuthorRequestDto dto){
        if(authorRepository.existsByFirstNameAndLastName(dto.firstName(), dto.lastName())){
            throw new IllegalArgumentException("Author already exists");
        }
        var newAuthor = authorMapper.toEntity(dto);
        var savedAuthor = authorRepository.save(newAuthor);
        return authorMapper.toDto(savedAuthor);
    }

    public List<AuthorResponseDto> getAuthors(Long id){
        List<Author> authors;

        if(id != null) {
            authors = authorRepository.findById(id).stream()
                    .toList();
        } else {
            authors = authorRepository.findAll();
        }
        return authorMapper.toDtoList(authors);
    }



}
