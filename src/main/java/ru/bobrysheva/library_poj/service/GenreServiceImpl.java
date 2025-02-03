package ru.bobrysheva.library_poj.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bobrysheva.library_poj.dto.BookDto;
import ru.bobrysheva.library_poj.dto.GenreDto;
import ru.bobrysheva.library_poj.entity.Genre;
import ru.bobrysheva.library_poj.repository.GenreRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    @Autowired
    private final GenreRepository genreRepository;


    @Override
    public GenreDto getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow();
        return convertToDto(genre);
    }

    private GenreDto convertToDto(Genre genre) {
        List<BookDto> bookDtoList = genre.getBooks()
                .stream().map(book -> BookDto.builder()
                        .id(book.getId())
                        .name(book.getName())
                        .genre(book.getGenre().getName())
//                      .authors(book.getAuthors().stream().toString())
                .build()).toList();


        return GenreDto.builder().id(genre.getId()).name(genre.getName()).books(bookDtoList).build();

    }
}
