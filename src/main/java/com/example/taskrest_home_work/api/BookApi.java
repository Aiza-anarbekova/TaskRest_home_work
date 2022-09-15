package com.example.taskrest_home_work.api;

import com.example.taskrest_home_work.dto.BookRequest;
import com.example.taskrest_home_work.dto.BookResponse;
import com.example.taskrest_home_work.dto.BookResponseView;
import com.example.taskrest_home_work.model.Book;
import com.example.taskrest_home_work.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/book")
public class BookApi {
    private final BookService service;

    @PostMapping
    public BookResponse save(@RequestBody BookRequest bookRequest) {
        return service.save(bookRequest);
    }

    @GetMapping("{id}")
    public BookResponse getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/find/{authorId}")
    public List<Book> findAll(@PathVariable Long authorId) {
        return service.findAllByAuthorId(authorId);
    }

    @PatchMapping("{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody BookRequest request) {
        return service.updateBook(id, request);
    }

    @DeleteMapping("{id}")
    public BookResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping
    public BookResponseView pagination(@RequestParam(name = "text", required = false) String text,
                                       @RequestParam int page,
                                       @RequestParam int size) {
        return service.pagination(text, page, size);
    }
}
