package com.example.snakegame;

/**
 * The type Quote generator.
 */
public class QuoteGenerator {

    // TODO: fill in the 'actual' quotes (not dummy quotes)

    private final String[] QUOTES = {
            "Just a random quote (1).",
            "Just a random quote (2).",
            "Just a random quote (3)."
    };

    /**
     * Gets random quote from the {@code QUOTES} array.
     * TODO: for now, the quotes are static. In the future, we will use an {@code API} to get random quotes.
     * @return the random quote
     */
    public String getRandomQuote() {
        int randomIndex = (int) (Math.random() * this.QUOTES.length);
        return this.QUOTES[randomIndex];
    }
    public String[] getQuotes() { return this.QUOTES; }
}
