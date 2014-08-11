package com.raido.task2.entity;

import com.raido.task2.element.TextElement;
import com.raido.task2.type.ElementType;

import java.util.List;

/**
 * This class represents a primitive text element
 * that is a leaf of the tree model of a parsed text.
 */

public class Leaf implements TextElement {

    private static final String WORD_DELIMITER = " ";

    private ElementType type;

    private String textContents;

    public Leaf(ElementType type, String textContents) {
        this.type = type;
        this.textContents = textContents;
    }

    @Override
    public void addChildElement(TextElement childElement) {
        throw new UnsupportedOperationException();
    }

    public String getContents() {
        return textContents;
    }

    @Override
    public void split(String textRepresentation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ElementType getType() {
        return type;
    }


    @Override
    public String getTextContents() {
        return textContents;
    }

    @Override
    public void setTextContents(String textContents) {
        this.textContents = textContents;
    }

    @Override
    public List<TextElement> getChildElements() {
        throw new UnsupportedOperationException();
    }

    @Override
    public TextElement getChildAt(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getChildCount() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        return getTextContents();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o == null) || (getClass() != o.getClass())) {
            return false;
        }

        Leaf leaf = (Leaf) o;

        if (!textContents.equals(leaf.textContents)) {
            return false;
        }

        if (type != leaf.type) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + textContents.hashCode();
        return result;
    }
}
