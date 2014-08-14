package com.raido.task2.main;

import com.raido.task2.analyzer.LexicalAnalyzer;
import com.raido.task2.entity.Composite;
import com.raido.task2.exception.LogicalException;
import com.raido.task2.exception.TechnicalException;
import com.raido.task2.operationlauncher.TextOperationsLauncher;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * This class is an application's entry point. It provides basic
 * configuration for logging and initiates text parsing.
 */

public class MainRunner {

    private static final String LOGGER_CONFIG_FILENAME = "log4j.xml";

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(MainRunner.class);
        new DOMConfigurator().doConfigure(LOGGER_CONFIG_FILENAME,
                LogManager.getLoggerRepository());
        LOGGER.setLevel(Level.INFO);
    }

    public static void main(String[] args) {

        LexicalAnalyzer analyzer = new LexicalAnalyzer();

        try {
            Composite parsedText = analyzer.analyzeText();

            try {
                TextOperationsLauncher launcher = new TextOperationsLauncher();
                launcher.performTextOperations(parsedText);

            } catch (TechnicalException e) {
                LOGGER.error(e);
            }

        } catch (LogicalException e) {
            LOGGER.error(e);
        } catch (TechnicalException e) {
            LOGGER.fatal(e);
        }

    }

}
