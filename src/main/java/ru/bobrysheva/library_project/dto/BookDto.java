package ru.bobrysheva.library_project.dto;

import lombok.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String genre;

}
