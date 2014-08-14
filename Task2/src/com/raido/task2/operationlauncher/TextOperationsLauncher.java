package com.raido.task2.operationlauncher;

import com.raido.task2.element.TextElement;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.textoperator.TextOperator;
import org.apache.log4j.Logger;

import java.util.HashSet;

public class TextOperationsLauncher {

    private static final Logger LOGGER =
            Logger.getLogger(TextOperationsLauncher.class);

    public void performTextOperations(TextElement text)
            throws TechnicalException {
        TextOperator textOperator = new TextOperator();


        String assembledText = textOperator.assembleText(text);
        LOGGER.info(assembledText);

        LOGGER.info("<< Searching for words of given length " +
                "in interrogative sentences: >>");
        HashSet<TextElement> wordsOfGivenLength =
                textOperator.findWordsOfLength(text, 4);
        LOGGER.info(wordsOfGivenLength);

        LOGGER.info("<< Removing all occurrences of the first letter in words " +
                "except the first letter itself: >>");
        textOperator.removeFirstLetterOccurrences(text);
        LOGGER.info(textOperator.assembleText(text));

    }

}
