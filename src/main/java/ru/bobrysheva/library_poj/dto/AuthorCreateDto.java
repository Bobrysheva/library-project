package ru.bobrysheva.library_poj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthorCreateDto {
    @Size(min = 3, max = 10)
    @NotBlank(message = "Необходимо указать имя")
    private String name;

    @NotBlank(message = "Необходимо указать фамилию")
    private String surname;
}
