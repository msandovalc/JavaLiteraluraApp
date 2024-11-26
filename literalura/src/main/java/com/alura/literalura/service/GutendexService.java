package com.alura.literalura.service;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
public class GutendexService {

    static final String BASE_URL = "https://gutendex.com/books";

    public GutendexService() {
    }

    public JsonNode searchBooks(String title) {
        // Construir la URL de la API
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .queryParam("search", title)
                .toUriString();

        // Realizar la solicitud HTTP
        RestTemplate restTemplate = new RestTemplate();
        JsonNode response = restTemplate.getForObject(url, JsonNode.class);

        return response;
    }

}
