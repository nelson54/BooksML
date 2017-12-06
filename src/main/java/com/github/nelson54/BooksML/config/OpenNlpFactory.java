package com.github.nelson54.BooksML.config;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetector;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public class OpenNlpFactory {

    public final ClassLoader classLoader;
    public final TokenNameFinderModel nameFinderModel;
    public final SentenceModel sentenceModel;

    public OpenNlpFactory(ClassLoader classLoader) throws IOException {
        this.classLoader = classLoader;
        this.nameFinderModel = getNameFinderModel();
        this.sentenceModel = getSentenceModel();
    }


    public NameFinderME getNameFinder() throws IOException {
        return new NameFinderME(nameFinderModel);
    }

    public SentenceDetectorME getSentenceDetector() {
        return new SentenceDetectorME(sentenceModel);
    }

    private TokenNameFinderModel getNameFinderModel() throws IOException {
        String pathToPersonModel = classLoader.getResource("en-ner-person.bin").getFile();
        InputStream modelIn = new FileInputStream(pathToPersonModel);
        TokenNameFinderModel tokenNameFinderModel = new TokenNameFinderModel(modelIn);
        modelIn.close();
        return tokenNameFinderModel;
    }

    private SentenceModel getSentenceModel() throws IOException {
        String pathToPersonModel = classLoader.getResource("en-sent.bin").getFile();
        InputStream modelIn = new FileInputStream(pathToPersonModel);
        SentenceModel model = new SentenceModel(modelIn);
        modelIn.close();
        return model;
    }
}
