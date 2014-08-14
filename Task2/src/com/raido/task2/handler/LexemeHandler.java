package com.raido.task2.handler;

import com.raido.task2.element.TextElement;
import com.raido.task2.type.ElementType;

import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeHandler implements ElementHandler {

    private ElementHandler successor;

    @Override
    public void setSuccessor(ElementHandler successor) {
        this.successor = successor;
    }

    @Override
    public String assembleText(TextElement text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public HashSet<TextElement> findWordsByLength(TextElement element, int length) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeFirstLetterOccurrences(TextElement lexeme) {

        if(lexeme.getType() != ElementType.WORD) {
            return;
        }

        char firstLetter = lexeme.getTextContents().charAt(0);
        String regex = buildFirstLetterRegex(firstLetter);
        Pattern pattern = Pattern.compile(regex);

        String wordText = lexeme.getTextContents();
        Matcher matcher = pattern.matcher(wordText);

        if(matcher.find()) {
            wordText = matcher.replaceAll("");
        }

        lexeme.setTextContents(wordText);
    }

    private String buildFirstLetterRegex(char firstLetter) {
        StringBuilder sb = new StringBuilder();
        sb.append("(?<=.)(").append(firstLetter).append("|");

        if (Character.isLowerCase(firstLetter)) {
            sb.append(Character.toUpperCase(firstLetter));
        } else {
            sb.append(Character.toLowerCase(firstLetter));
        }

        sb.append(")");

        return sb.toString();
    }

}
