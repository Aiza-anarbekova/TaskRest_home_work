package com.example.taskrest_home_work.repository;

import com.example.taskrest_home_work.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    @Query("select b from  Book b  where b.author.id=:id")
    List<Book> findBooksByAuthorId(Long id);

    @Query("select b  from Book b where upper(b.name) like concat('%',:text,'%')")
    List<Book> searchByName(@Param("text") String text, Pageable pageable);
}
