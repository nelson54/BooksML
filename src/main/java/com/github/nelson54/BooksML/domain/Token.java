package com.github.nelson54.BooksML.domain;

import lombok.Data;
import opennlp.tools.util.Span;

@Data
public class Token {
    private final Span position;
    private final TokenType type;

    public Token(Span position, TokenType type) {
        this.position = position;
        this.type = type;
    }
}
