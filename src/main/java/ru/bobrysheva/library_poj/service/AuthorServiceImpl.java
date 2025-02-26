package ru.bobrysheva.library_poj.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.model.Author;
import ru.bobrysheva.library_poj.repository.AuthorRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorDto getAuthorById(Long id) {
        log.info("Поиск автора по ключу {}", id);
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            AuthorDto authorDto = convertEntityToDto(author.get());
            log.trace("Автор с идентификатором {} найден", id);
            return authorDto;
        } else log.error("Автор с идентификатором {} не найден", id);
        throw new NoSuchElementException("No value present");
    }

    @Override
    public List<AuthorDto> findAuthorsByNameV1(String name) {
        log.info("Поиск авторов с именем {}", name);
        List<Author> authors = authorRepository.findAuthorsByName(name);
        List<AuthorDto> foundAuthors = authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
        if (foundAuthors.isEmpty()) {
            log.warn("Авторов с именем {} в библиотеке не нашлось", name);
            throw new NoSuchElementException("No value present");
        } else log.info("В библиотеке нашлось {} авторов с именем {}", foundAuthors.size(), name);
        return authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AuthorDto> findAuthorsByNameV2(String name) {
        Optional<Author> authors = authorRepository.findAuthorByNameBySql(name);
        log.info("Поиск авторов по имени {}", name);
        List<AuthorDto> foundAuthors = authors.stream().map(this::convertToDto)
                .collect(Collectors.toList());
        if (foundAuthors.isEmpty()) {
            log.error("Авторов с именем {} не найдено", name);
            throw new NoSuchElementException("No value present");
        } else log.info("Найдено {} авторов с именем {}", foundAuthors.size(), name);
        return foundAuthors;
    }

    @Override
    public List<AuthorDto> findAuthorsBySurnameV3(String surname) {
        Specification<Author> specification = Specification.where(new Specification<Author>() {
            @Override
            public Predicate toPredicate(Root<Author> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("surname"), surname);
            }
        });
        List<Author> authors = authorRepository.findAll(specification);
        if (authors.isEmpty()) {
            log.error("Авторов с фамилией {} в базе данных не нашлось", surname);
            throw new NoSuchElementException("No value present");
        }else {
            log.info("Найдено {} авторов с фамилией {}", authors.size(), surname);
        return authors.stream().map(this::convertToDto)
                .collect(Collectors.toList()); }
    }

    @Override
    public List<Author> findAuthorsBySurname(String surname) {
        Optional<Author> foundAuthors = authorRepository.findAuthorBySurnameBySql(surname);
        if (foundAuthors.isEmpty()) {
            log.info("Авторы с фамилией {} в репозитории не найдены ", surname);
            throw new NoSuchElementException("No value present");
        } else log.info("Авторы с фамилией {} найдены в репозитории", surname);
        List<Author> authors = foundAuthors.stream().toList();
        return authors;
    }

    @Override
    public List<AuthorDto> findByBooksId(Long bookId) {
        log.info("Поиск автора книги с идентификатором {}", bookId);
        List<Author> authors = authorRepository.findByBooksId(bookId);
        List<AuthorDto> findByBooksId = authors.stream().map(this::convertToDto).collect(Collectors.toList());
        if (findByBooksId.isEmpty()) {
            log.info("Автор книги с идентификатором {} не найден", bookId);
        } else
            log.info("Автор книги с идентификатором {} успешно найден", bookId);
        return findByBooksId;
    }

    @Override
    public AuthorDto createAuthor(AuthorCreateDto authorCreateDto) {
        log.info("Создание нового автора");
        Author author = authorRepository.save(convertDtoToEntity(authorCreateDto));
        AuthorDto authorDto = convertEntityToDto(author);
        log.info("Новый автор c именем {} и фамилией {} создан и сохранен с идентификатором {}", author.getName(),
                author.getSurname(), author.getId());
        return authorDto;
    }

    @Override
    public AuthorDto updateAuthor(AuthorUpdateDto authorUpdateDto) {
        log.info("Приступаю к обновлению сведений об авторе с идентификатором {}", authorUpdateDto.getId());
        Optional<Author> foundAuthor = authorRepository.findById(authorUpdateDto.getId());
        Author author = null;
        if (foundAuthor.isEmpty()) {
            log.error("Автор с идентификатором {}  в базе данных не найден", authorUpdateDto.getId());
        } else
            author = foundAuthor.get();
        log.info("Автор с идентификатором {} найден в базе данных. Приступаю к обновлению.", authorUpdateDto.getId());
        author.setName(authorUpdateDto.getName());
        author.setSurname(authorUpdateDto.getSurname());
        Author savedAuthor = authorRepository.save(author);
        AuthorDto authorDto = convertEntityToDto(savedAuthor);
        log.info("Сведения об авторе с идентификатором {} успешно обновлены", author.getId());
        return authorDto;
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isEmpty()) {
            log.error("Автор с идентификатором {} в базе данных не найден", id);
        } else
            authorRepository.deleteById(id);
        log.info("Автор с идентификатором {} успешно удален из базы данных", id);
    }

    public Author convertDtoToEntity(AuthorCreateDto authorCreateDto) {
        log.info("Конвертирую AuthorCreateDto в Author");
        return Author.builder()
                .name(authorCreateDto.getName())
                .surname(authorCreateDto.getSurname())
                .build();
    }

    @Override
    public AuthorDto convertEntityToDto(Author author) {
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

    private AuthorDto convertToDto(Author author) {
        List<BookDto> bookDtoList = author.getBooks()
                .stream()
                .map(book -> BookDto.builder()
                        .name(book.getName())
                        .id(book.getId())
                        .build())
                .toList();
        log.info("Конвертирую Author в AuthorDto");
        return AuthorDto.builder()
                .books(bookDtoList)
                .id(author.getId())
                .name(author.getName())
                .surname(author.getSurname())
                .build();
    }
}
