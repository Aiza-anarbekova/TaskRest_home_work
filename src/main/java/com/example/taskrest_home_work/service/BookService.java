package com.example.taskrest_home_work.service;

import com.example.taskrest_home_work.dto.BookRequest;
import com.example.taskrest_home_work.dto.BookResponse;
import com.example.taskrest_home_work.dto.BookResponseView;
import com.example.taskrest_home_work.exception.NotFoundException;
import com.example.taskrest_home_work.model.Author;
import com.example.taskrest_home_work.model.Book;
import com.example.taskrest_home_work.repository.AuthorRepository;
import com.example.taskrest_home_work.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final AuthorRepository authorRepository;

    public BookResponse save(BookRequest request) {
        Book book = create(request);
        return bookResponse(repository.save(book));

    }

    public Book create(BookRequest request) {
        Book book = new Book();
        Author author = authorRepository.findById(request.getAuthorId()).orElseThrow(
                () -> new NotFoundException("String.format(\"Author with id = %s not found \",id)"));
        book.setName(request.getName());
        book.setPublicationDate(request.getPublicationDate());
        book.setAuthor(author);
        book.setPublisher(request.getPublisher());
        book.setBookYear(Period.between(book.getPublicationDate(), LocalDate.now()).getYears());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        repository.save(book);
        return book;
    }

    public BookResponse bookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setName(book.getName());
        bookResponse.setId(book.getId());
        bookResponse.setGenre(book.getGenre());
        bookResponse.setBookYear(book.getBookYear());
        bookResponse.setDescription(book.getDescription());
        bookResponse.setPublisher(book.getPublisher());
        bookResponse.setPublicationDate(book.getPublicationDate());
        return bookResponse;

    }

    public BookResponse getById(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException("String.format(\"Book with id =%s not found \",id)"));
        return bookResponse(book);
    }

    public Book update(Book book, BookRequest request) {
        book.setName(request.getName());
        book.setPublicationDate(request.getPublicationDate());
        book.setPublisher(request.getPublisher());
        book.setBookYear(Period.between(book.getPublicationDate(), LocalDate.now()).getYears());
        book.setDescription(request.getDescription());
        book.setGenre(request.getGenre());
        repository.save(book);
        return book;
    }

    public BookResponse updateBook(Long id, BookRequest request) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException("String.format(\"Book with id =%s not found \",id)"));
        Book book1 = update(book, request);
        return bookResponse(repository.save(book1));
    }

    public BookResponse delete(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new NotFoundException("String.format(\"Book with id =%s not found \",id)"));
        book.setAuthor(null);
        repository.delete(book);
        return bookResponse(book);
    }

    public List<Book> findAllByAuthorId(Long id) {
        return repository.findBooksByAuthorId(id);
    }

    public List<Book> search(String name, Pageable pageable) {
        String text = name == null?"":name;
        return repository.searchByName(text.toUpperCase(), pageable);
    }

    public List<BookResponse> getAll(List<Book> books) {
        List<BookResponse> responses = new ArrayList<>();
        for (Book b : books) {
            responses.add(bookResponse(b));
        }
        return responses;
    }

    public BookResponseView pagination(String text, int page, int size) {
        BookResponseView bookResponseView = new BookResponseView();
        Pageable pageable = PageRequest.of(page-1, size);
        bookResponseView.setBookResponseList(getAll(search(text,pageable)));
        return bookResponseView;
    }
}
