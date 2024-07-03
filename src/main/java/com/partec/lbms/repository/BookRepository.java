package com.partec.lbms.repository;

import com.partec.lbms.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingOrAuthorContainingOrIsbnContaining(String keyword, String keyword1, String keyword2);
}
