package com.example.taskrest_home_work.dto;

import com.example.taskrest_home_work.enums.Genre;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Getter
@Setter
public class BookRequest {
     private String name;
     @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
     private LocalDate publicationDate;
     private String description;
     private Genre genre ;
     private String publisher;
     private Long authorId;
}
