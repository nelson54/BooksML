package com.github.nelson54.BooksML.job;

import com.github.nelson54.BooksML.utils.ParagraphScanner;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainJob implements ApplicationRunner {

    private final BookJob bookJob;
    private final ClassLoader classLoader;

    public MainJob(BookJob bookJob, ClassLoader classLoader) {
        this.bookJob = bookJob;
        this.classLoader = classLoader;
    }

    private ParagraphScanner getScanner(String resource) throws FileNotFoundException {
        String pathToBook = classLoader.getResource(resource).getFile();
        File file = new File(pathToBook);
        return new ParagraphScanner(file);
    }

    @Override
    public void run(ApplicationArguments args) {
        try {
            ParagraphScanner scanner = getScanner("war-and-peace.txt");

            List<String> paragraphs = new ArrayList<>();


            while(scanner.hasNext()) {
                paragraphs.add(scanner.next());
            }

            Integer nameCount = paragraphs
                    .parallelStream()
                    .map(bookJob::run)
                    .map(List::size)
                    .reduce((memo, size) -> memo + size).get();

        } catch (Exception e) {

        }


    }
}
