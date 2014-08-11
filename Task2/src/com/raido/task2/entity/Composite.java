package com.raido.task2.entity;

import com.raido.task2.element.TextElement;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.operation.TextOperations;
import com.raido.task2.parser.TextParser;
import com.raido.task2.type.ElementType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a compound text element, such as
 * a paragraph or a sentence.
 *
 * <p>It is capable of recursively applying operations
 * to its child elements.</p>
 *
 */

public class Composite implements TextElement {
    private List<TextElement> childElements;

    private ElementType type;

    public Composite(ElementType type, String textRepresentation)
            throws TechnicalException {
        this.type = type;
        childElements = new ArrayList<TextElement>();
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

    @Override
    public void addChildElement(TextElement childElement) {
        childElements.add(childElement);
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
