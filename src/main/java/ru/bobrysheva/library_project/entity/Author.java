package ru.bobrysheva.library_project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String surname;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

}
