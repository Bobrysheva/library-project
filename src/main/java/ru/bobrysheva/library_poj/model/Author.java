package ru.bobrysheva.library_poj.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Setter
    @Column(nullable = false)
    public String name;

    @Setter
    @Column(nullable = false)
    public String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
