package com.github.nelson54.BooksML.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ParagraphScanner {
    private final Scanner scanner;
    private StringBuffer buffer;

    public ParagraphScanner(File file) throws FileNotFoundException {
        scanner = new Scanner(file);
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public String next() {
        StringBuffer buffer = new StringBuffer();
        String line;
        while(scanner.hasNextLine()) {
            line = scanner.nextLine();
            if(line != null && line.trim().isEmpty() && buffer.length() > 0) {
                break;
            } else if(line != null && !line.trim().isEmpty()) {
                buffer.append(line);
                buffer.append(" ");
            }
        }

        return buffer.toString().trim();
    }
}
