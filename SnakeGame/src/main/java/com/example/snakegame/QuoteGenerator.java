/***************************************************************************************************
 * The Snake Game - Jörmungandr
 * File: {@code QuoteGenerator.java} - a class that generates random quotes from a text file.
 * Members: Michal Spano, Malte Bengtsson, Simone Graziosi, Feride Hansson, Anna Mäkinen, Katinka Romanus
 * For DIT094 Mini Project: Team Programming; SEM@GU.
 ***************************************************************************************************/

package com.example.snakegame;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

import java.util.List;
import java.util.ArrayList;

/**
 * The type Quote generator.
 */
public class QuoteGenerator {

    /**
     * The path to the project resources directory.
     */
    public static final String PROJECT_RESOURCE = "SnakeGame/src/main/resources/";

    private final List<String> QUOTES;
    private final String QUOTE_SOURCE;

    /**
     * Instantiates a new Quote generator.
     *
     * @param quoteFile the file with the quotes
     */
    public QuoteGenerator(String quoteFile) throws IOException {
        this.QUOTE_SOURCE = PROJECT_RESOURCE + quoteFile;

        // handle if the quotes file is not found and display a message to the user
        try {
            this.QUOTES = loadQuotes();
        } catch (IOException e) {
            throw new IOException("Cannot get resource file: " + quoteFile);
        }
    }

    /**
     * Load quotes to a list.
     * TODO: for now, the quotes are static. In the future, we will use an {@code API} to get random quotes.
     *
     * @return the list
     * @throws IOException the IO exception if the file is not found
     */
    public List<String> loadQuotes() throws IOException {
        List<String> tempQuotes = new ArrayList<>();

        String line;
        BufferedReader reader = new BufferedReader(new FileReader(this.QUOTE_SOURCE));

        while ((line = reader.readLine()) != null)
            tempQuotes.add(line);

        reader.close();
        return tempQuotes;
    }

    /**
     * Gets random quote from the {@code quotes} array.
     *
     * @return the random quote
     */
    public String getRandomQuote() {
        int randomIndex = (int) (Math.random() * this.QUOTES.size());
        return this.QUOTES.get(randomIndex);
    }

    /**
     * Get the path to the quote source file.
     *
     * @return the quote file
     */
    public String getQuoteFile() { return this.QUOTE_SOURCE; }

    /**
     * Get the List of the {@code quotes}.
     *
     * @return the string []
     */
    public List<String> getQUOTES() { return this.QUOTES; }
}
