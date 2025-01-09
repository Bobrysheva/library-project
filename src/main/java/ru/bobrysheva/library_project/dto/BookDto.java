package ru.bobrysheva.library_project.dto;

import lombok.*;


@Builder
@Getter
public class BookDto {
    private Long id;
    private String name;
    private String genre;

}
