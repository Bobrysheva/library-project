package ru.bobrysheva.library_poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bobrysheva.library_poj.entity.Genre;


public interface GenreRepository extends JpaRepository <Genre, Long> {

}
