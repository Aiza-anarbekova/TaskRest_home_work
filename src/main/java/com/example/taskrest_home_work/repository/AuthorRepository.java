package com.example.taskrest_home_work.repository;

import com.example.taskrest_home_work.model.Author;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("select a from Author a where upper(a.fullName) like concat('%',:text,'%') " +
            "or upper(a.nationality)like  concat('%',:text,'%') ")
    List<Author> searchAuthor(@Param("text") String text,
                              Pageable pageable);

}
