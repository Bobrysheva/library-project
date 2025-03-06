package ru.bobrysheva.library_poj.controllers.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bobrysheva.library_poj.dto.AuthorCreateDto;
import ru.bobrysheva.library_poj.dto.AuthorDto;
import ru.bobrysheva.library_poj.dto.AuthorUpdateDto;
import ru.bobrysheva.library_poj.service.AuthorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Tag(name = "Авторы", description = "Управление перечнями авторов")
public class AuthorRestController {

    private final AuthorService authorService;

    @Operation (summary = "Поиск автора по id", description = "Метод позволяет найти " +
    "автора по его id, типа Long")
    @GetMapping("/authors/{id}")
    AuthorDto getAuthorById(@PathVariable("id") Long id) {
        return authorService.getAuthorById(id);
    }

    @Operation (summary = "Поиск автора по имени версия 1", description = "Позволяет найти " +
    "автора по введенному имени через автогенерацию запроса")
    @GetMapping("/authors/name-1/{name}")
    List<AuthorDto> findAuthorsByName1(@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV1(name);
    }

    @Operation (summary = "Поиск автора по имени версия 2", description = "Позволяет найти " +
    "автора по введенному имени через аннотоацию @Query")
    @GetMapping("/authors/name-2/{name}")
    List<AuthorDto> findAuthorsByName2(@PathVariable("name") String name) {
        return authorService.findAuthorsByNameV2(name);
    }

    @Operation (summary = "Поиск автора по фамилии", description = "Позволяет найти автора " +
    "по введенной фамилии посредством спецификации")
    @GetMapping("/authors/name-3/{surname}")
    List<AuthorDto> findAuthorsByName3(@PathVariable("surname") String surname) {
        return authorService.findAuthorsBySurnameV3(surname);
    }

    @Operation (summary = "Поиск автора по id книги")
    @GetMapping("/authors")
    List<AuthorDto> findByBooksId(@RequestParam Long id) {
        return authorService.findByBooksId(id);
    }

    @Operation (summary = "Создание автора", description = "Позволяет создать нового " +
    "автора через json, применяется валидация введенных данных для объекта AuthorDto")
    @PostMapping("/authors")
    ResponseEntity<?> createAuthor(@Valid @RequestBody AuthorCreateDto authorCreateDto, BindingResult result) {
        if (result.hasErrors())
        {
            Map<String, String> errors = new HashMap<>();
            result.getAllErrors().forEach(objectError -> {
                FieldError fieldError = (FieldError) objectError;
                errors.put(fieldError.getField(), objectError.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body (errors);
        }
            return ResponseEntity.ok().body(authorService.createAuthor(authorCreateDto));

    }

    @Operation (summary = "Обновление сведений об авторе", description = "Позволяет " +
    "обновить сведения об авторе через json")
    @PutMapping("/authors")
    AuthorDto updateAuthor(@RequestBody AuthorUpdateDto authorUpdateDto) {
        return authorService.updateAuthor(authorUpdateDto);
    }

    @Operation (summary = "Удаление автора из базы данных")
    @DeleteMapping("/authors/{id}")
    void deleteAuthor (@PathVariable("id") Long id) {
        authorService.deleteAuthor(id);
    }
}