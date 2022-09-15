package com.example.taskrest_home_work.api;

import com.example.taskrest_home_work.dto.AuthorRequest;
import com.example.taskrest_home_work.dto.AuthorResponse;
import com.example.taskrest_home_work.dto.AuthorResponseView;
import com.example.taskrest_home_work.model.Author;
import com.example.taskrest_home_work.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/author")
@RequiredArgsConstructor
public class AuthorApi {
    private final AuthorService service;

    @PostMapping
    public AuthorResponse save(@RequestBody AuthorRequest request) {
        return service.save(request);
    }

    @GetMapping("{id}")
    public AuthorResponse findOne(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/all")
    public List<Author> findAll() {
        return service.findAll();
    }

    @PatchMapping("{id}")
    public AuthorResponse update(@PathVariable Long id, @RequestBody AuthorRequest request) {
        return service.updateAuthor(id, request);
    }

    @DeleteMapping("{id}")
    public AuthorResponse delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping
    public AuthorResponseView pagination(@RequestParam(name = "text", required = false) String text,
                                         @RequestParam int page,
                                         @RequestParam int size) {
        return service.pagination(text, page, size);
    }

    /**
     * {
     *     "name":"ak keme",
     *     "publisher":"Aitmatov",
     *     "genre":"ROMAN",
     *     "description":"best",
     *     "publicationDate":"1990-12-12"
     *
     *
     *    "firstName": "Chyngyz",
     *     "lastName": "Aitmatov",
     *     "nationality": "kyrgyz",
     *     "gender": "MALE",
     *     "dateOfBirth":"1928-12-12"
     * }
     */
}
