package com.raido.task2.textoperator;

import com.raido.task2.chaincreator.HandlerChainCreator;
import com.raido.task2.element.TextElement;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.handler.*;

import java.util.HashSet;

public class TextOperator {

    private ElementHandler firstHandler;

    public TextOperator() throws TechnicalException {
        firstHandler = HandlerChainCreator.getInstance().createHandlerChain();
    }

    public HashSet<TextElement> findWordsOfLength(TextElement text, int length) {

        return firstHandler.findWordsByLength(text, length);
    }

    public void removeFirstLetterOccurrences(TextElement text) {
        firstHandler.removeFirstLetterOccurrences(text);
    }

    public String assembleText(TextElement text) {
        return firstHandler.assembleText(text);
    }

}
