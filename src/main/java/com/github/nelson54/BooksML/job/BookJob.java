package com.github.nelson54.BooksML.job;


import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookJob {
    private final NameFinderME nameFinder;
    private final List<String> names;
    private final ClassLoader classLoader;
    private final TokenNameFinderModel model;

    public BookJob(NameFinderME nameFinder, ClassLoader classLoader) throws IOException {
        String pathToPersonModel = classLoader.getResource("en-ner-person.bin").getFile();
        InputStream is = new FileInputStream(pathToPersonModel);
        this.model = new TokenNameFinderModel(is);
        is.close();

        this.classLoader = classLoader;
        this.nameFinder = nameFinder;
        names = new ArrayList<>();
    }

    public List<String> run(String paragraph) {
        NameFinderME nameFinder = new NameFinderME(model);
        if(paragraph != null && !paragraph.isEmpty()) {
            String[] words = paragraph.split(" ");


            Span nameSpans[] = nameFinder.find(words);

            List<String> names = new ArrayList<>();
            StringBuilder sb;

            for (Span span : nameSpans) {
                sb = new StringBuilder();

                for (int i = span.getStart(); i < span.getEnd(); i++) {
                    sb.append(words[i]);

                    if (i < span.getEnd() - 1) {
                        sb.append(" ");
                    } else {
                        String name = sb.toString();
                        names.add(name);
                        System.out.println(name);
                    }
                }
            }
        }

        return names;
    }
}
