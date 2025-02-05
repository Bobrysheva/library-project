package ru.bobrysheva.library_poj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.entity.Author;
import ru.bobrysheva.library_poj.repository.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id).orElseThrow();
        return convertToDto (author);
    }

    @Override
    public List <AuthorDto> findAuthorsByName(String name) {
        List <Author> authors = authorRepository.findAuthorsByName(name);
        return authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDto> findByBooks_Id (Long bookId) {
        List <Author> authors = authorRepository.findByBooks_Id(bookId);
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private AuthorDto convertToDto (Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
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
