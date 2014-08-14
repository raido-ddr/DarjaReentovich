package com.raido.task2.handler;

import com.raido.task2.element.TextElement;

import java.util.HashSet;
import java.util.List;

public class TextHandler implements ElementHandler {

    private ElementHandler successor;

    @Override
    public String assembleText(TextElement text) {
        StringBuilder sb = new StringBuilder();

        for(TextElement paragraph : text.getChildElements()) {
            sb.append(successor.assembleText(paragraph));
            sb.append("\n\r");
        }

        return sb.toString();
    }

    @Override
    public void setSuccessor(ElementHandler successor) {
        this.successor = successor;
    }

    @Override
    public HashSet<TextElement> findWordsByLength(TextElement text, int length) {
        List<TextElement> paragraphList = text.getChildElements();
        HashSet<TextElement> wordsOfGivenLength = new HashSet<>();

        for(TextElement paragraph : paragraphList) {
            wordsOfGivenLength.addAll(successor.findWordsByLength(paragraph, length));
        }

        return wordsOfGivenLength;
    }

    @Override
    public void removeFirstLetterOccurrences(TextElement text) {
        for(TextElement paragraph : text.getChildElements()) {
            successor.removeFirstLetterOccurrences(paragraph);
        }
    }


}
