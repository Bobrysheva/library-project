package ru.bobrysheva.library_poj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreDto {
    private Long id;
    private String name;

    private List <BookDto> books;
}
