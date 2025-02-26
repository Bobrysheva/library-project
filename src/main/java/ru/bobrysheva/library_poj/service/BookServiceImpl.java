package ru.bobrysheva.library_poj.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

import ru.bobrysheva.library_poj.dto.BookCreateDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.BookUpdateDto;
import ru.bobrysheva.library_poj.model.Author;
import ru.bobrysheva.library_poj.model.Book;
import ru.bobrysheva.library_poj.repository.BookRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final GenreService genreService;
    private final AuthorService authorService;

    @Override
    public BookDto getByNameV1(String name) {
        Book book = bookRepository.findBookByName(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV2(String name) {
        Book book = bookRepository.findBookByNameBySql(name).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto getByNameV3(String name) {
        Specification<Book> specification = Specification.where(new Specification<Book>() {
            @Override
            public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("name"), name);
            }
        });
        Book book = bookRepository.findOne(specification).orElseThrow();
        return convertEntityToDto(book);
    }

    @Override
    public BookDto createBookDto(BookCreateDto bookCreateDto) {
        Book book = bookRepository.save(convertDtoToEntity(bookCreateDto));
        return convertEntityToDto(book);
    }

    @Override
    public BookDto updateBookDto(BookUpdateDto bookUpdateDto) {


//        Set<Author> authors = bookUpdateDto.getAuthorsSurname().stream()
//                .map(authorService::findAuthorsBySurnameV3)
//                .flatMap(List::stream)
//                .map(this::authorService.convertAuthorDtoToEntity)
//                .collect(Collectors.toSet());


        Book book = bookRepository.findById(bookUpdateDto.getId()).orElseThrow();
        book.setName(bookUpdateDto.getName());
        book.setGenre(genreService.getGenreByName(bookUpdateDto.getGenre()));

//        book.setAuthors(authors);

        return convertEntityToDto(book);
    }


    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {

        List <Book> books = bookRepository.findAll();
        return books.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private Book convertDtoToEntity (BookCreateDto bookCreateDto) {
        return Book.builder()
                .name(bookCreateDto.getName())
                .genre(genreService.getGenreByName(bookCreateDto.getGenre()))
                .build();
    }

    private BookDto convertEntityToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre().getName())
                .build();
    }
}