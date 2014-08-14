package com.raido.task2.handler;

import com.raido.task2.element.TextElement;
import com.raido.task2.type.ElementType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

public class SentenceHandler implements ElementHandler {

    private ElementHandler successor;

    private static HashSet<String> spacePrecededSigns;

    private static HashSet<String> boundToWordsSigns;

    static {
        spacePrecededSigns =
                new HashSet<>(Arrays.asList("\"", "(", "[", "{", "\'",
                        "+", "=", "<", ">"));

        boundToWordsSigns =
                new HashSet<>(Arrays.asList("(", "[", "{", "-"));

    }

    @Override
    public String assembleText(TextElement sentence) {
        StringBuilder sb = new StringBuilder();

        ListIterator<TextElement> iterator =
                sentence.getChildElements().listIterator();

        do {
            sb.append(iterator.next());
            iterator.previous();

            if(! boundToWordsSigns.contains(iterator.next().getTextContents())) {
                if(iterator.hasNext()) {
                    if(needsPrecedingWhiteSpace(iterator.next())) {
                        sb.append(" ");
                    }
                    iterator.previous();
                }
            }
        } while(iterator.hasNext());

        return sb.toString();
    }

    private boolean needsPrecedingWhiteSpace(TextElement lexeme) {

        return (lexeme.getType() == ElementType.WORD)
                || (spacePrecededSigns.contains(lexeme.getTextContents()));
    }

    @Override
    public void setSuccessor(ElementHandler successor) {
        this.successor = successor;
    }

    @Override
    public HashSet<TextElement> findWordsByLength(TextElement sentence, int length) {

        HashSet<TextElement> wordsOfGivenLength = new HashSet<>();
        if(!isInterrogative(sentence)) {
            return wordsOfGivenLength;
        }

        List<TextElement> lexemeList = sentence.getChildElements();
        for(TextElement lexeme : lexemeList) {
            if((lexeme.getType() == ElementType.WORD)
                    && (lexeme.getTextContents().length() == length)) {
                wordsOfGivenLength.add(lexeme);
            }
        }

        return wordsOfGivenLength;
    }

    @Override
    public void removeFirstLetterOccurrences(TextElement sentence) {
        for(TextElement word : sentence.getChildElements()) {
            successor.removeFirstLetterOccurrences(word);
        }
    }

    private boolean isInterrogative(TextElement sentence) {
        TextElement lastLexeme =
                sentence.getChildAt(sentence.getChildCount() - 1);

        return ("?".equals(lastLexeme.getTextContents()) );
    }
}
