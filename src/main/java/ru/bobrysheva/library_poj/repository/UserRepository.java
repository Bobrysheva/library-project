package ru.bobrysheva.library_poj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bobrysheva.library_poj.model.User;

public interface UserRepository extends JpaRepository <User, Long> {
    User findUserByEmail (String email);

    User findUserById (Long id);
}
