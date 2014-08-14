package com.raido.task2.entity;

import com.raido.task2.element.TextElement;
import com.raido.task2.type.ElementType;

import java.util.List;

public class Leaf implements TextElement {

    private ElementType type;

    private String textContents;

    public Leaf(ElementType type, String textContents) {
        this.type = type;
        this.textContents = textContents;
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
