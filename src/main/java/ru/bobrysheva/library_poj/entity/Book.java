package ru.bobrysheva.library_poj.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column (nullable = false)
    private String name;

    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "genre_id")
    private Genre genre;

    @Setter
    @ManyToMany
    @JoinTable (
            name = "author_book",
            inverseJoinColumns = @JoinColumn (name = "author_id", referencedColumnName = "id"),
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id") )

    private Set <Author> authors;


}
