package com.raido.task2.handler;

import com.raido.task2.element.TextElement;

import java.util.HashSet;

public interface ElementHandler {

    void setSuccessor(ElementHandler successor);

    String assembleText(TextElement text);

    HashSet<TextElement> findWordsByLength(TextElement element, int length);

    void removeFirstLetterOccurrences(TextElement element);

}
