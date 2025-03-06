package ru.bobrysheva.library_poj.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.GenreDto;
import ru.bobrysheva.library_poj.model.Genre;
import ru.bobrysheva.library_poj.repository.GenreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }

    @Override
    public Genre getGenreByName(String name) {
        Genre genre = genreRepository.findByName(name).orElseThrow();
        return genre;
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream().map(book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .authors(book.getAuthors().stream().map(author -> AuthorDto.builder()
                                .name(author.getName())
                                .surname(author.getSurname())
                                .build()).toList())

                        .build()).toList();

        return GenreDto.builder().id(genre.getId()).name(genre.getName()).books(bookDtoList).build();
    }
}
