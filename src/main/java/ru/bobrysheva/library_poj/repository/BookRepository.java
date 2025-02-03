package ru.bobrysheva.library_poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bobrysheva.library_poj.entity.Book;

import java.util.Optional;


public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByName(String name);
}