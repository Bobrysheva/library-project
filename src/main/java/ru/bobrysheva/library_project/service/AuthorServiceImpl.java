package ru.bobrysheva.library_project.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_project.dto.AuthorDto;
import ru.bobrysheva.library_project.dto.BookDto;
import ru.bobrysheva.library_project.entity.Author;
import ru.bobrysheva.library_project.repository.AuthorRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto (author);
    }
    private AuthorDto convertToDto (Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .genre(book.getGenre().getName())
                        .name(book.getName())
                        .id(book.getId())
                        .build()
                ).toList();
        return  AuthorDto.builder()
                .books(bookDtoList)
                .id (author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}