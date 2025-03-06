package ru.bobrysheva.library_poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.bobrysheva.library_poj.model.Author;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    List <Author> findAuthorsByName (String name);
  
    List <Author> findByBooksId(Long bookId);

    @Query(nativeQuery = true, value = "SELECT * FROM AUTHOR WHERE name = ?")
    Optional<Author> findAuthorByNameBySql(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM AUTHOR WHERE surname = ?")
    Optional<Author> findAuthorBySurnameBySql(String surname);
}