package com.alura.literalura.menu;

import java.util.*;
import java.util.stream.Collectors;

import com.alura.literalura.dto.BookDTO;
import com.alura.literalura.model.Book;
import com.alura.literalura.repository.BookRepository;
import com.alura.literalura.service.BookService;
import com.alura.literalura.service.GutendexService;
import com.alura.literalura.util.DataParser;
import com.fasterxml.jackson.databind.JsonNode;

public class Menu {

    private final BookRepository repository;
    private final GutendexService gutendexService;

    public Menu(BookRepository repository) {
        this.repository = repository;
        this.gutendexService = new GutendexService();
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n=== Menú de Opciones ===");
            System.out.println("1. Buscar libro por título en la base de datos");
            System.out.println("2. Listar todos los libros registrados");
            System.out.println("3. Listar libros por idioma");
            System.out.println("4. Buscar libro por título en la web y almacenarlo en la base de datos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (option) {
                case 1 -> buscarLibroPorTitulo(scanner);
                case 2 -> listarTodosLosLibros();
                case 3 -> listarLibrosPorIdioma(scanner);
                case 4 -> buscarYGuardarLibroDesdeWeb(scanner);
                case 5 -> {
                    System.out.println("Saliendo del sistema...");
                    exit = true;
                }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private void buscarLibroPorTitulo(Scanner scanner) {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        var books = repository.findByTitleContainingIgnoreCase(title);
        if (books.isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
        } else {
            //books.forEach(book -> System.out.println(book.toString()));
            books.stream()
                    .map(book -> String.format(
                            "Título: %s%nAutor: %s%nIdioma: %s%nDescargas: %d%n-----------------------",
                            book.getTitle(), book.getAuthor(), book.getLanguage(), book.getDownloads()
                    ))
                    .forEach(System.out::println);
        }
    }

    private void listarTodosLosLibros() {
        var books = repository.findAll();
        if (books.isEmpty()) {
            System.out.println("No hay libros registrados en la base de datos.");
        } else {
            books.stream()
                    .map(book -> String.format(
                            "Título: %s%nAutor: %s%nIdioma: %s%nDescargas: %d%n-----------------------",
                            book.getTitle(), book.getAuthor(), book.getLanguage(), book.getDownloads()
                    ))
                    .forEach(System.out::println);
        }
    }

    private void listarLibrosPorIdioma(Scanner scanner) {
        System.out.print("Ingrese el idioma (ES, EN, FR, PT): ");
        String language = scanner.nextLine();
        var books = repository.findByLanguage(language);
        if (books.isEmpty()) {
            System.out.println("No se encontraron libros en ese idioma.");
        } else {
            books.stream()
                    .map(book -> String.format(
                            "Título: %s%nAutor: %s%nIdioma: %s%nDescargas: %d%n-----------------------",
                            book.getTitle(), book.getAuthor(), book.getLanguage(), book.getDownloads()
                    ))
                    .forEach(System.out::println);
        }
    }

    private void buscarYGuardarLibroDesdeWeb(Scanner scanner) {
        System.out.print("Ingrese el título del libro a buscar en la web: ");
        String title = scanner.nextLine();

        Book book = new Book();
        DataParser dataParser = new DataParser();
        BookService bookService = new BookService(this.repository);

        // Buscar en la API de Gutendex
        var response = gutendexService.searchBooks(title.replace(" ", "+"));

        // Procesar los resultados
        if (response != null && response.has("results")) {
            JsonNode bookNode = response.get("results").get(0);
            String bookTitle = bookNode.get("title").asText();
            String author = dataParser.extractAuthor(bookNode.get("authors"));
            String language = dataParser.extractLanguage(bookNode.get("languages"));
            int downloads = bookNode.get("download_count").asInt();

            book = bookService.registerBook(new BookDTO(bookTitle, author, language, downloads));

            if(book != null)
            {
                System.out.println("Title: " + book.getTitle() +", Author: " + book.getAuthor() +
                        ", Dowloads: " + book.getDownloads() + ", Languages: " + book.getLanguage());
            }
        }
        else
        {
            System.out.println("No se encontraron libros con ese título en la web.");
        }
    }
}
