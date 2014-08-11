package com.raido.task2.analyzer;

import com.raido.task2.entity.Composite;
import com.raido.task2.exception.LogicalException;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.handler.LexemeHandler;
import com.raido.task2.handler.ParagraphHandler;
import com.raido.task2.handler.SentenceHandler;
import com.raido.task2.handler.TextHandler;
import com.raido.task2.type.ElementType;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;


public class LexicalAnalyzer {

    private static final String SOURCE_FILE_NAME =
            "resources/test.txt";

    private static final String EXCEPTION_MESSAGE_FILE_NAME =
            "exception_message";

    private static Logger logger =
            Logger.getLogger(LexicalAnalyzer.class);


    public void performTextAnalysis()
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

        Composite parsedText = parseText(textToParse);

        LexemeHandler wordHandler = new LexemeHandler();
        SentenceHandler sentenceHandler = new SentenceHandler();
        sentenceHandler.setSuccessor(wordHandler);

        ParagraphHandler paragraphHandler = new ParagraphHandler();
        paragraphHandler.setSuccessor(sentenceHandler);

        TextHandler textHandler = new TextHandler();
        textHandler.setSuccessor(paragraphHandler);

//        /*HashSet<TextElement> wordsOfGivenLength =
//                textHandler.findWordsByLength(parsedText, 4);
//
//        System.out.println(wordsOfGivenLength);
        //System.out.println(textHandler.assembleText(parsedText));

        textHandler.removeFirstLetterOccurrences(parsedText);

        logger.info(textHandler.assembleText(parsedText));
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
