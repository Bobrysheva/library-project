package ru.bobrysheva.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bobrysheva.library_project.entity.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}