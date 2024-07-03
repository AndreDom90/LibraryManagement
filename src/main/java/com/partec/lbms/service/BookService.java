package com.partec.lbms.service;

import com.partec.lbms.entity.Book;
import com.partec.lbms.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository.findByTitleContainingOrAuthorContainingOrIsbnContaining(keyword, keyword, keyword);
    }
}
