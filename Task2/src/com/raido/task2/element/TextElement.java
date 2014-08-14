package com.raido.task2.element;

import com.raido.task2.exception.TechnicalException;
import com.raido.task2.type.ElementType;

import java.util.List;

/**
 * Implementing this interface allows classes to describe
 * different types of text elements - both composite and primitive.
 *
 * <p>Methods imposed by this interface make up basic functionality
 * that any text element should provide according to the application's
 * convention.</p>
 * <p>Depending on element type, they might not support operations
 * dealing with child elements, that composite elements consist of.
 * </p>
 */

public interface TextElement {

    void split(String textRepresentation) throws TechnicalException;

    ElementType getType();

    String getTextContents();

    void setTextContents(String textContents);

    List<TextElement> getChildElements();

    TextElement getChildAt(int index);

    int getChildCount();

}
