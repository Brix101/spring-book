package com.brix.bookMs.book;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class BookConfig {
    @Bean
    CommandLineRunner commandLineRunner(BookRepository bookRepository) {
        return args -> {
            Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", "978-3-16-148410-0", 5, LocalDate.of(1925, 4, 10));

            Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", "978-0-06-112008-4", 3, LocalDate.of(1960, 7, 11));

            Book book3 = new Book("1984", "George Orwell", "978-0-14-103614-4", 7, LocalDate.of(1949, 6, 8));

            Book book4 = new Book("Moby Dick", "Herman Melville", "978-1-85326-082-9", 2, LocalDate.of(1851, 10, 18));

            Book book5 = new Book("Pride and Prejudice", "Jane Austen", "978-1-85326-082-8", 4, LocalDate.of(1813, 1, 28));

            bookRepository.saveAll(List.of(book1, book2, book3, book4, book5));
        };
    }
}
