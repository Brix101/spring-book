package com.brix.bookMs.book;

import com.brix.bookMs.exception.BadRequestException;
import com.brix.bookMs.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());
        if (bookOptional.isPresent()) {
            throw new BadRequestException("ISBN already taken");
        }
        return bookRepository.save(book);
    }

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with id " + bookId + " does not exists"));
    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);
        if (!exists) {
            throw new NotFoundException("Book with id " + bookId + " does not exists");
        }

        bookRepository.deleteById(bookId);
    }

    @Transactional
    public Book updateBook(Long bookId, Book book) {
        Book _book = bookRepository.findById(bookId).orElseThrow(() -> new NotFoundException("Book with id " + bookId + " does not exists"));

        _book.setTitle(book.getTitle());
        _book.setAuthor(book.getAuthor());
        _book.setQuantity(book.getQuantity());
        _book.setPublishedDate(book.getPublishedDate());

        if (!Objects.equals(_book.getIsbn(), book.getIsbn())) {
            Optional<Book> bookOptional = bookRepository.findBookByIsbn(book.getIsbn());
            if (bookOptional.isPresent()) {
                throw new BadRequestException("ISBN already taken");
            }
            _book.setIsbn(book.getIsbn());
        }

        return _book;
    }

}
