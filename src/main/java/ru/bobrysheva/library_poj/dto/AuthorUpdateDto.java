package ru.bobrysheva.library_poj.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Getter
public class AuthorUpdateDto {
    private Long id;
    private String name;
    private String surname;
}
