package ru.bobrysheva.library_poj.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
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

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        Author author = authorRepository.save (convertDtoToEntity(authorCreateDto));
        AuthorDto authorDto = convertEntityToDto(author);
        return authorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        Author author = authorRepository.findById(authorUpdateDto.getId()).orElseThrow();
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDto authorDto = convertEntityToDto(savedAuthor);
        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    @Override
     public Author convertAuthorDtoToEntity (AuthorDto authorDto) {
        return Author.builder()
                .name(authorDto.getName())
                .surname(authorDto.getSurname())
                .build();
    }
    public Author convertDtoToEntity (AuthorCreateDto authorCreateDto) {
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }
    private AuthorDto convertEntityToDto(Author author) {
        List<BookDto> bookDtoList = null;
        if (author.getBooks() != null) {
            bookDtoList = author.getBooks()
                    .stream()
                    .map(book -> BookDto.builder()
                            .genre(book.getGenre().getName())
                            .name(book.getName())
                            .id(book.getId())
                            .build())
                    .toList();
        }
        AuthorDto authorDto = AuthorDto.builder()
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .books(bookDtoList)
                .build();
        return authorDto;
    }

    private AuthorDto convertToDto (Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .build())
                .toList();

        return  AuthorDto.builder()
                .books(bookDtoList)
                .id (author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }

}
