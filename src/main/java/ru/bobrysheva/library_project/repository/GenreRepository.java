package ru.bobrysheva.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bobrysheva.library_project.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository <Genre, Long> {
}
