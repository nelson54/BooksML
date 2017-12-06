package com.github.nelson54.BooksML.domain;

import lombok.Data;

import java.util.List;

public @Data class Sentence {
    private final Book book;
    private final String sentence;
    List<Person> people;

    public Sentence(Book book, String sentence) {
        this.book = book;
        this.sentence = sentence;
    }

    public void addPerson(String[] name) {
        String.join(" ", name);
    }
}
