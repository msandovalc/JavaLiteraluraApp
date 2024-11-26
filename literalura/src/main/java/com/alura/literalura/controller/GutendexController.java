package com.alura.literalura.controller;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.service.GutendexService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GutendexController {

    @Autowired
    private GutendexService gutendexService;

    @GetMapping("/search-books")
    public JsonNode searchBooks(@RequestParam String title) {
        return gutendexService.searchBooks(title);
    }
}
