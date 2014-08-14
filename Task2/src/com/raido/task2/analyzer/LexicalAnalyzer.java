package com.raido.task2.analyzer;

import com.raido.task2.entity.Composite;
import com.raido.task2.exception.LogicalException;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.type.ElementType;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class LexicalAnalyzer {

    private static final String SOURCE_FILE_NAME =
            "resources/text/test2.txt";

    private static final String EXCEPTION_MESSAGE_FILE_NAME =
            "exception_message";


    public Composite analyzeText()
            throws LogicalException, TechnicalException {

        String textToParse = null;

        try {
            textToParse =
                    readTextFromFile(SOURCE_FILE_NAME, StandardCharsets.UTF_8);
            if(textToParse.isEmpty()) {
                ResourceBundle messageBundle =
                        ResourceBundle.getBundle(EXCEPTION_MESSAGE_FILE_NAME);
                throw new LogicalException(messageBundle.getString("empty_file"));
            }
        } catch (IOException e) {
            throw new TechnicalException(e);
        }

        return parseText(textToParse);
    }

    private String readTextFromFile(String path, Charset charset)
            throws IOException
    {
        byte[] fileContents = Files.readAllBytes(Paths.get(path));
        return new String(fileContents, charset);
    }

    private Composite parseText(String textToParse)
            throws TechnicalException {
        return new Composite(ElementType.TEXT, textToParse);
    }

}
