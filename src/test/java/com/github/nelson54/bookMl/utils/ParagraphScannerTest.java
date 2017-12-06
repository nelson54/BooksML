package com.github.nelson54.bookMl.utils;

import com.github.nelson54.BooksML.BookProcessor;
import com.github.nelson54.BooksML.utils.ParagraphScanner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitJupiterConfig;

import java.io.File;
import java.io.FileNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringJUnitJupiterConfig
@SpringBootTest(classes= BookProcessor.class)
class ParagraphScannerTest {

    @Autowired
    public ClassLoader classLoader;

    @Test
    void next() throws FileNotFoundException {
        String warAndPeaceFile = classLoader.getResource("paragraphs.txt").getFile();

        ParagraphScanner scanner = new ParagraphScanner(new File(warAndPeaceFile));

        while(scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }
}