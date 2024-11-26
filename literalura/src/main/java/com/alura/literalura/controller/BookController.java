package com.alura.literalura.controller;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Book;
import com.alura.literalura.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public Book registerBook(@RequestBody BookDTO bookDTO) {
        return service.registerBook(bookDTO);
    }

    @GetMapping
    public List<Book> listAllBooks() {
        return service.listAllBooks();
    }

    @GetMapping("/language/{language}")
    public List<Book> listBooksByLanguage(@PathVariable String language) {
        return service.listBooksByLanguage(language);
    }

    @GetMapping("/title/{title}")
    public Book findBookByTitle(@PathVariable String title) {
        return service.findBookByTitle(title);
    }
}
