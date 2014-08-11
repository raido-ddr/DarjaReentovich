package com.raido.task2.parser;

import com.raido.task2.entity.Composite;
import com.raido.task2.element.TextElement;
import com.raido.task2.entity.Leaf;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.type.ElementType;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.raido.task2.type.ElementType.*;

/**
 * This class provides static methods that
 * can be used to split compound text elements
 * further into a number of child elements,
 * compound or primitive.
 *
 * <p>The <code>splitElement</code> method is capable
 * of determining the type of the element supplied as
 * its parameter.</p>
 */

public class TextParser {

    private static final String PARAGRAPH_PATTERN_KEY = "paragraph_pattern";

    private static final String SENTENCE_PATTERN_KEY = "sentence_pattern";

    private static final String LEXEME_PATTERN_KEY = "lexeme_pattern";

    private static final String WORD_PATTERN_KEY = "word_pattern";

    private static final String PUNCTUATION_PATTERN_KEY = "punctuation_pattern";

    private static final ResourceBundle regexBundle =
            ResourceBundle.getBundle("regex");

    private static final String EXCEPTION_MESSAGE_FILE =
            "exception_message";

    private static final String LISTING_PATTERN_KEY = "listing_pattern";

    private static Pattern paragraphPattern =
            Pattern.compile(getParagraphPattern());

    private static Pattern sentencePattern =
            Pattern.compile(getSentencePattern());

    private static Pattern lexemePattern =
            Pattern.compile(getLexemePattern());

    private static Pattern punctuationPattern =
            Pattern.compile(getPunctuationPattern());

    private static Pattern wordPattern =
            Pattern.compile(getWordPattern());

  /*  private static Pattern listingPattern =
            Pattern.compile(getListingPattern());*/

    public static List<TextElement> splitElement(ElementType type,
            String textRepresentation) throws TechnicalException {

        switch(type) {
            case TEXT:
                return splitIntoParagraphs(textRepresentation);
            case PARAGRAPH:
                return splitIntoSentences(textRepresentation);
            case SENTENCE:
                return splitIntoLexemes(textRepresentation);
            default:
                ResourceBundle messageBundle =
                        ResourceBundle.getBundle(EXCEPTION_MESSAGE_FILE);
                throw new TechnicalException(messageBundle.getString("empty_element"));
        }
    }

    public static List<TextElement> splitIntoParagraphs(String textRepresentation)
            throws TechnicalException {

        List<TextElement> paragraphList = new ArrayList<TextElement>();

        Pattern paragraphPattern = Pattern.compile(getParagraphPattern(),
                Pattern.MULTILINE);
        String[] paragraphsTextArray =
                paragraphPattern.split(textRepresentation);

        for(String paragraphText : paragraphsTextArray) {
            paragraphList.add(new Composite(PARAGRAPH, paragraphText));
        }

        return paragraphList;
    }

    public static List<TextElement> splitIntoSentences(String textRepresentation)
            throws TechnicalException {

        List<TextElement> sentenceList = new ArrayList<TextElement>();

        Pattern sentencePattern = Pattern.compile(getSentencePattern());
        String[] sentencesTextArray =
                sentencePattern.split(textRepresentation);

        for(String sentenceText : sentencesTextArray) {
            sentenceList.add(new Composite(SENTENCE, sentenceText));
        }

        return sentenceList;
    }

    public static List<TextElement> splitIntoLexemes(String textRepresentation) {
        List<TextElement> lexemeList = new ArrayList<TextElement>();

        Matcher matcher = lexemePattern.matcher(textRepresentation);

        while(matcher.find()) {
            String lexemeText = matcher.group();
            if (isWord(lexemeText)) {
                lexemeList.add(new Leaf(WORD, lexemeText));
            } else if(isPunctuationSign(lexemeText)) {
                lexemeList.add(new Leaf(PUNCTUATION_SIGN, lexemeText));
            }/* else if(isListing(lexemeText)) {
                lexemeList.add(new Leaf(LISTING, lexemeText));
            }*/
        }

        return lexemeList;
    }

    private static boolean isWord(String textRepresentation) {
        Matcher matcher = wordPattern.matcher(textRepresentation);
        return (matcher.find());
    }

    private static boolean isPunctuationSign(String textRepresentation) {
        Matcher matcher =
                punctuationPattern.matcher(textRepresentation);
        return (matcher.find());
    }

    /*private static boolean isListing(String textRepresentation) {
        Matcher matcher =
                listingPattern.matcher(textRepresentation);
        return (matcher.find());
    }*/

    private static String getWordPattern() {
        return regexBundle.getString(WORD_PATTERN_KEY);
    }

    private static String getPunctuationPattern() {
        return regexBundle.getString(PUNCTUATION_PATTERN_KEY);
    }

    private static String getParagraphPattern() {
        return regexBundle.getString(PARAGRAPH_PATTERN_KEY);
    }

    private static String getSentencePattern() {
        return regexBundle.getString(SENTENCE_PATTERN_KEY);
    }

    private static String getLexemePattern() {
        return regexBundle.getString(LEXEME_PATTERN_KEY);
    }

    private static String getListingPattern() {
        return regexBundle.getString(LISTING_PATTERN_KEY);
    }

}
