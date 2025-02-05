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
public class BookDto {
    private Long id;
    private String name;
//    private String genre;

    private List <AuthorShortDto> authors;
}
