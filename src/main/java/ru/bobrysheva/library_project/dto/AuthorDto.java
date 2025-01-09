package ru.bobrysheva.library_project.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;

    private List<BookDto> books;
}
