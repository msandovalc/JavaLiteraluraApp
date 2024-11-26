package com.alura.literalura.service;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.exception.BookNotFoundException;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.GutendexService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.alura.literalura.service.GutendexService.*;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book registerBook(BookDTO bookDTO) {

        Book book = null;

        if (repository.existsByTitle(bookDTO.title())) {
            System.out.println("Book title: "  + bookDTO.title() + "already exists in the database.");
            //throw new IllegalArgumentException("Book already exists in the database.")
        }
        else
        {

            // Crear instancia de Book directamente usando el constructor
            Book book1 = new Book(
                    null, // ID será autogenerado por JPA
                    bookDTO.title(),
                    bookDTO.author(),
                    bookDTO.language(),
                    bookDTO.downloads(),
                    null // Fecha de publicación puede ser opcional o se establece después
            );

            book = repository.save(book1);
        }

        return book;
    }

    public List<BookDTO> searchBooksInDatabase(String title) {
        return repository.findByTitleContainingIgnoreCase(title)
                .stream()
                .map(book -> new BookDTO(
                        book.getTitle(),
                        book.getAuthor(),
                        book.getLanguage(),
                        book.getDownloads()
                ))
                .collect(Collectors.toList());
    }

    public List<Book> listAllBooks() {
        return repository.findAll();
    }

    public List<Book> listBooksByLanguage(String language) {
        return repository.findByLanguage(language);
    }

    public Book findBookByTitle(String title) {
        return repository.findAll()
                .stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found."));
    }
}
