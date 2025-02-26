package ru.bobrysheva.library_poj.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateDto {
    private Long id;
    private String name;
    private String genre;
  
    private List <String> authorsSurname;
}
