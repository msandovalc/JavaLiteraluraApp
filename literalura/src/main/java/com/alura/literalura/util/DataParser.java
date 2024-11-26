package com.alura.literalura.util;

import com.fasterxml.jackson.databind.JsonNode;

public class DataParser {

    public DataParser() {
    }

    public String extractAuthor(JsonNode authorsNode) {
        if (authorsNode != null && authorsNode.isArray() && !authorsNode.isEmpty()) {
            JsonNode authorNode = authorsNode.get(0);
            return authorNode.get("name").asText();
        }
        return "Unknown Author";
    }

    public String extractLanguage(JsonNode languagesNode) {
        if (languagesNode != null && languagesNode.isArray() && !languagesNode.isEmpty()) {
            return languagesNode.get(0).asText();
        }
        return "Unknown";
    }
}
