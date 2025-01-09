package ru.bobrysheva.library_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class BookDto {
    private Long id;
    private String name;
    private String genre;

}
