package com.raido.task2.entity;

import com.raido.task2.element.TextElement;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.parser.TextParser;
import com.raido.task2.type.ElementType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Composite implements TextElement {
    private List<TextElement> childElements;

    private ElementType type;

    public Composite(ElementType type, String textRepresentation)
            throws TechnicalException {
        this.type = type;
        childElements = new ArrayList<>();
        split(textRepresentation);
    }

    @Override
    public ElementType getType() {
        return type;
    }

    @Override
    public String getTextContents() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTextContents(String textContents) {
        throw new UnsupportedOperationException();
    }

    public List<TextElement> getChildElements() {
        return Collections.unmodifiableList(childElements);
    }

    @Override
    public TextElement getChildAt(int index) {
        return childElements.get(index);
    }

    @Override
    public int getChildCount() {
        return childElements.size();
    }

    @Override
    public void split(String textRepresentation)
            throws TechnicalException {
       setChildElements(TextParser.splitElement(type, textRepresentation));
    }

    private void setChildElements(List<TextElement> textElements) {
        if (textElements != null) {
            childElements.addAll(textElements);
        }
    }
}
