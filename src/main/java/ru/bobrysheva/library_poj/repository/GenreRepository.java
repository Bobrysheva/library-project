package ru.bobrysheva.library_poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bobrysheva.library_poj.entity.Book;
import ru.bobrysheva.library_poj.entity.Genre;

import java.util.Optional;


public interface GenreRepository extends JpaRepository <Genre, Long> {
    Optional <Genre> findByName (String name);
}
