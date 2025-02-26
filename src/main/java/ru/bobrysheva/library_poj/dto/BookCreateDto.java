package ru.bobrysheva.library_poj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateDto {
    Long id;
    String name;
    String genre;
}