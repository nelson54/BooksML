package com.github.nelson54.BooksML.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Sentence {
    private final String[] sentence;
    private List<Token> tokens;

    public Sentence(String[] sentence) {
        this.sentence = sentence;
    }

    public List<Token>  getAllPersons() {
        return getTokensByType(TokenType.PERSON);
    }

    public List<Token>  getAllLocations() {
        return getTokensByType(TokenType.LOCATION);
    }

    private List<Token> getTokensByType(TokenType type) {
        List<Token> persons = new ArrayList<>();

        for(Token token: tokens) {
            if(token.getType().equals(type)) {
                persons.add(token);
            }
        }

        return persons;
    }
}
