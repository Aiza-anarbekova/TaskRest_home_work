package com.example.taskrest_home_work.service;

import com.example.taskrest_home_work.dto.AuthorRequest;
import com.example.taskrest_home_work.dto.AuthorResponse;
import com.example.taskrest_home_work.dto.AuthorResponseView;
import com.example.taskrest_home_work.exception.NotFoundException;
import com.example.taskrest_home_work.model.Author;
import com.example.taskrest_home_work.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class  AuthorService {
    private final AuthorRepository repository;

    public AuthorResponse save(AuthorRequest request) {
        Author author = new Author();
        author.setId(author.getId());
        author.setFullName(request.getFirstName() + " " + request.getLastName());
        author.setGender(request.getGender());
        author.setNationality(request.getNationality());
        author.setDateOfBirth(request.getDateOfBirth());
        Author author1 = repository.save(author);
        String[] name = author1.getFullName().split(" ", 2);
        String firstName = name[0];
        String lastName = name[1];
        return new AuthorResponse(author1.getId(),
                author1.getFullName(),
                firstName,
                lastName,
                author1.getNationality(),
                author1.getGender(),
                Period.between(author1.getDateOfBirth(),LocalDate.now()).getYears()
        );

    }

    public AuthorResponse getById(Long id) {
        Author author1 = repository.findById(id).orElseThrow(
                () -> new NotFoundException("String.format(\"Author with id = %s not found\",id)"));
        String[] name = author1.getFullName().split(" ", 2);
        String firstName = name[0];
        String lastName = name[1];
        return new AuthorResponse(author1.getId(),
                author1.getFullName(),
                firstName,
                lastName,
                author1.getNationality(),
                author1.getGender(),
                Period.between( author1.getDateOfBirth(),LocalDate.now()).getYears()
        );

    }

    public AuthorResponse updateAuthor(Long id,AuthorRequest request){
      Author author =  repository.findById(id).orElseThrow(
                () -> new NotFoundException("String.format(\"Author with id = %s not found\",id)"));
      Author author1 =update(author,request);
      repository.save(author1);
        String[] name = author1.getFullName().split(" ", 2);
        String firstName = name[0];
        String lastName = name[1];
        return new AuthorResponse(author1.getId(),
                author1.getFullName(),
                firstName,
                lastName,
                author1.getNationality(),
                author1.getGender(),
                Period.between(author1.getDateOfBirth(),LocalDate.now()).getYears()
        );
    }

    public Author update(Author author,AuthorRequest request){
        author.setFullName(request.getFirstName() + " " + request.getLastName());
        author.setGender(request.getGender());
        author.setNationality(request.getNationality());
        author.setDateOfBirth(request.getDateOfBirth());
        return repository.save(author);
    }

    public AuthorResponse delete(Long id){
        Author author = repository.findById(id).orElseThrow(
                ()->new NotFoundException("String.format(\"Author with id= %s not found\",id)"));
        repository.delete(author);
        String[] name = author.getFullName().split(" ", 2);
        String firstName = name[0];
        String lastName = name[1];
        return new AuthorResponse(
                author.getId(),
                author.getFullName(),
                firstName,
                lastName,
                author.getNationality(),
                author.getGender(),
                Period.between( author.getDateOfBirth(),LocalDate.now()).getYears()
        );
    }

    public List<Author> findAll(){
      return repository.findAll();
    }

    public List<Author> search(String name, Pageable pageable){
        String text = name==null?"":name;
        return repository.searchAuthor(text.toUpperCase(),pageable);
    }

    public List<AuthorResponse> getAll(List<Author> authors){
        List<AuthorResponse> responses = new ArrayList<>();
        for (Author author:authors) {
            String[] name = author.getFullName().split(" ", 2);
            String firstName = name[0];
            String lastName = name[1];
            responses.add(
                    new AuthorResponse(
                    author.getId(),
                    author.getFullName(),
                    firstName,
                    lastName,
                    author.getNationality(),
                    author.getGender(),
                    Period.between( author.getDateOfBirth(),LocalDate.now()).getYears()));
        }
        return responses;
    }

    public AuthorResponseView pagination(String text,int page,int size){
        AuthorResponseView authorResponseView = new AuthorResponseView();
        Pageable pageable = PageRequest.of(page-1,size);
        authorResponseView.setAuthorResponse(getAll(search(text,pageable)));
        return authorResponseView;
    }
}

