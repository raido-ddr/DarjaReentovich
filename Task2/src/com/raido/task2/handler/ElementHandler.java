package com.raido.task2.handler;

import com.raido.task2.element.TextElement;

import java.util.HashSet;

/**
 * Created by Raido_DDR on 8/9/2014.
 */
public interface ElementHandler {

    void setSuccessor(ElementHandler successor);

    String assembleText(TextElement text);

    HashSet<TextElement> findWordsByLength(TextElement element, int length);

    void removeFirstLetterOccurrences(TextElement element);

}
