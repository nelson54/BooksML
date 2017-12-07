package com.github.nelson54.BooksML.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Paragraph {
    private String paragraph;
    private List<Sentence> sentences;

    public Paragraph(String paragraph) {
        this.paragraph = paragraph;
        sentences = new ArrayList<>();
    }

    public void addSentence(Sentence sentence) {
        sentences.add(sentence);
    }

    public String getParagraph() {
        return paragraph;
    }
}
