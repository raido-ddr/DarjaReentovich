package com.raido.task2.main;

import com.raido.task2.analyzer.LexicalAnalyzer;
import com.raido.task2.exception.LogicalException;
import com.raido.task2.exception.TechnicalException;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class is an application's entry point. It provides basic
 * configuration for logging and causes application to perform
 * text parsing.
 */

public class MainRunner {

    private static final String LOGGER_CONFIG_FILENAME = "log4j.xml";

    private static Logger logger;

    static {
        logger = Logger.getLogger(MainRunner.class);
        new DOMConfigurator().doConfigure(LOGGER_CONFIG_FILENAME,
                LogManager.getLoggerRepository());
        logger.setLevel(Level.INFO);
    }

    public static void main(String[] args) {

        LexicalAnalyzer analyzer = new LexicalAnalyzer();

        try {
            analyzer.performTextAnalysis();
        } catch (LogicalException e) {
            logger.error(e);
        } catch (TechnicalException e) {
            logger.fatal(e);
        }

    }

}
