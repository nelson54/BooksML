package com.github.nelson54.BooksML.config;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class NlpLoaderConfig {

    private final ClassLoader classLoader;

    public NlpLoaderConfig(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Bean
    public NameFinderME getNameFinder() throws IOException {
        String pathToPersonModel = classLoader.getResource("en-ner-person.bin").getFile();
        InputStream is = new FileInputStream(pathToPersonModel);
        TokenNameFinderModel model = new TokenNameFinderModel(is);
        is.close();

        return new NameFinderME(model);
    }
}
