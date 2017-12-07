package com.github.nelson54.BooksML.job;

import com.github.nelson54.BooksML.config.OpenNlpFactory;
import com.github.nelson54.BooksML.domain.Paragraph;
import com.github.nelson54.BooksML.domain.Sentence;
import opennlp.tools.sentdetect.SentenceDetectorME;
import org.springframework.stereotype.Service;

@Service
public class SentenceFactoryJob {
    private final OpenNlpFactory openNlpFactory;

    public SentenceFactoryJob(OpenNlpFactory openNlpFactory) {
        this.openNlpFactory = openNlpFactory;

    }

    void run (Paragraph paragraph) {
        SentenceDetectorME sentenceDetector = this.openNlpFactory.getSentenceDetector();

        String[] sentences = sentenceDetector.sentDetect(paragraph.getParagraph());

        String[] words;
        for (String sentence : sentences) {
            words = splitAndSanatizeWords(sentence);
            paragraph.addSentence(new Sentence(words));
        }
    }

    String[] splitAndSanatizeWords(String sentence) {
        String[] words = sentence.split(" ");

        for(int i=0; i<words.length;i++) {
            words[i] = sanitizeWord(words[i]);
        }

        return words;
    }

    String sanitizeWord(String word) {
        return word;
    }
}
