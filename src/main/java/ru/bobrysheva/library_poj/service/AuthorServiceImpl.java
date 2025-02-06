package ru.bobrysheva.library_poj.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.entity.Author;
import ru.bobrysheva.library_poj.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;
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
    public List <AuthorDto> findAuthorsByNameV1(String name) {
        List <Author> authors = authorRepository.findAuthorsByName(name);
        return authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDto> findAuthorsByNameV2(String name) {
        Optional<Author> authors = authorRepository.findAuthorByNameBySql(name);
        return authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDto> findAuthorsBySurnameV3(String surname) {
        Specification <Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("surname"), surname);
            }
        });
        Optional<Author> authors = authorRepository.findAuthorBySurnameBySql(surname);
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
