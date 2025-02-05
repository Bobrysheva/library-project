package ru.bobrysheva.library_poj.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthorDto {
    private Long id;
    private String name;
    private String surname;

    private List <BookDto> books;
}
