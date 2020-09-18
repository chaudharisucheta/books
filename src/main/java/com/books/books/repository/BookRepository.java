package com.books.books.repository;

import com.books.books.model.Book;
import com.books.books.response.ApiResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BookRepository extends JpaRepository<Book, Integer> {
        @Modifying
        @Query(value = "update book set rating=?1 where book_no=?2",nativeQuery = true)
        public int updateB(Integer rating, Integer id);
}
