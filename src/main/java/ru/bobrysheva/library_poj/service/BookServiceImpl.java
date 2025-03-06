package ru.bobrysheva.library_poj.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.BookCreateDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.BookUpdateDto;
import ru.bobrysheva.library_poj.model.Author;
import ru.bobrysheva.library_poj.model.Book;
import ru.bobrysheva.library_poj.repository.BookRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public BookDto getByNameV1(String name) {
        log.info("Поиск книги с названием {}, способ 1", name);
        Optional<Book> foundBook = bookRepository.findBookByName(name);
        if (foundBook.isPresent()) {
            log.info("Книга с названием {} успешно найдена", name);
            return convertEntityToDto(foundBook.get());
        } else {
            log.error("Книга с названием {}, не найдена", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto getByNameV2(String name) {
        log.info("Поиск книги с названием {}, способ 2", name);
        Optional<Book> foundBook = bookRepository.findBookByNameBySql(name);
        if (foundBook.isEmpty()) {
            log.error("Книга с названием {}, не найдена", name);
            throw new NoSuchElementException("No value present");
        } else {
            log.info("Книга с названием {} успешно найдена", name);
            return convertEntityToDto(foundBook.get());
        }
    }

    @Override
    public BookDto getByNameV3(String name) {
        log.info("Поиск книги с названием {}, способ 3", name);
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });

        Optional<Book> book = bookRepository.findOne(specification);
        if (book.isPresent()) {
            log.info("Книга с названием {} успешно найдена", name);
            return convertEntityToDto(book.get());
        } else {
            log.error("Книга с названием {}, не найдена", name);
            throw new NoSuchElementException("No value present");
        }
    }

    @Override
    public BookDto createBookDto(BookCreateDto bookCreateDto) {
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        log.info("Создана новая книга с названием {}",book.getName());
        return convertEntityToDto(book);
    }

    @Override
    public BookDto updateBookDto(BookUpdateDto bookUpdateDto) {
        Set<Author> authors = new HashSet<>();
        for (String s : bookUpdateDto.getAuthorsSurname()) {
            List<Author> foundAuthors = authorService.findAuthorsBySurname(s);
            for (Author author : foundAuthors) {
                authors.add(author);
            }
        }

        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        book.setGenre(genreService.getGenreByName(bookUpdateDto.getGenre()));
        book.setAuthors(authors);
        Book savedBook = bookRepository.save(book);
        log.info("В книгу с названием {} внесены изменения", savedBook.getName());
        return convertEntityToDto(savedBook);
    }


    @Override
    public void deleteBook(Long id) {
        log.info("Книга с идентификатором {} удалена", id);
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        log.info("Перечень всех книг передан на отображение");

        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private Book convertDtoToEntity(BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genreService.getGenreByName(bookCreateDto.getGenre()))
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        List<AuthorDto> gotAuthors = new ArrayList<>();
        for (Author author : book.getAuthors()) {
            gotAuthors.add(authorService.convertEntityToDto(author));
        }
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .authors(gotAuthors)
                .build();
    }

}