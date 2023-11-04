package com.brix.bookMs.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //    @Query("SELECT b FROM Book b where b.isbn = ?1")
    Optional<Book> findBookByIsbn(String isbn);
}
