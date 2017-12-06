package com.github.nelson54.BooksML.job;


import com.github.nelson54.BooksML.config.OpenNlpFactory;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.util.Span;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookJob {
    private final OpenNlpFactory openNlpFactory;
    private final List<String> names;

    public BookJob(OpenNlpFactory openNlpFactory) {
        this.openNlpFactory = openNlpFactory;
        names = new ArrayList<>();
    }

    public List<String> run(String paragraph) throws IOException {
        List<String> names = new ArrayList<>();

        String[] sentences = getSentences(paragraph);

        for (String sentence : sentences) {
            String[] words = getWords(sentence);

            names.addAll(getNames(words));
        }

        return names;
    }

    private String[] getSentences(String paragraph) {
        SentenceDetectorME detector = openNlpFactory.getSentenceDetector();
        return detector.sentDetect(paragraph);
    }

    private String[] getWords(String sentence) {
        return sentence.split(" ");
    }

    private List<String> getNames(String[] words) throws IOException {
        StringBuilder sb;
        NameFinderME nameFinder = openNlpFactory.getNameFinder();
        List<String> names = new ArrayList<>();

        Span[] nameSpans = nameFinder.find(words);

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
        return names;
    }
}
