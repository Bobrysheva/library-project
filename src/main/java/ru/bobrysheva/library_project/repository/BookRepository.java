package ru.bobrysheva.library_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bobrysheva.library_project.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
