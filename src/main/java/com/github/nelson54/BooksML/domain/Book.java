package com.github.nelson54.BooksML.domain;

import lombok.Data;

public @Data class Book {
    private String author;
    private String series;
    private String title;
    private String fileName;
}
