package com.raido.task2.handler;

import com.raido.task2.element.TextElement;

import java.util.HashSet;
import java.util.List;

public class ParagraphHandler implements ElementHandler {

    private ElementHandler successor;

    @Override
    public String assembleText(TextElement paragraph) {
        StringBuilder sb = new StringBuilder();

        for(TextElement sentence : paragraph.getChildElements()) {
            sb.append(successor.assembleText(sentence));
            sb.append(" ");
        }

        return sb.toString();
    }

    @Override
    public void setSuccessor(ElementHandler successor) {
        this.successor = successor;
    }

    @Override
    public HashSet<TextElement> findWordsByLength(TextElement paragraph, int length) {
        List<TextElement> sentenceList = paragraph.getChildElements();
        HashSet<TextElement> wordsOfGivenLength = new HashSet<>();

        for(TextElement sentence : sentenceList) {
            wordsOfGivenLength.addAll(successor.findWordsByLength(sentence, length));
        }

        return wordsOfGivenLength;
    }

    @Override
    public void removeFirstLetterOccurrences(TextElement paragraph) {
        for(TextElement sentence : paragraph.getChildElements()) {
            successor.removeFirstLetterOccurrences(sentence);
        }
    }

}
