package ru.bobrysheva.library_poj.dto;

import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;

    private List <BookDto> books;
}
