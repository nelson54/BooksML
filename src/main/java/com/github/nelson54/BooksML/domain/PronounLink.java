package com.github.nelson54.BooksML.domain;

import lombok.Data;
import opennlp.tools.util.Span;

@Data
public class PronounLink {
    private Span pronoun;
    private Token person;

    public PronounLink(Span pronoun) {
        this.pronoun = pronoun;
    }

    public PronounLink(Span pronoun, Token person) {
        this.pronoun = pronoun;
        this.person = person;
    }
}
