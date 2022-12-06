package com.example.snakegame;

public class QuoteGenerator {

    // TODO: fill in the 'actual' quotes (not dummy quotes)

    private final String[] QUOTES = {
            "Just a random quote (1).",
            "Just a random quote (2).",
            "Just a random quote (3)."
    };

    // this method returns a random quote from the QUOTES array
    public String getRandomQuote() {
        int randomIndex = (int) (Math.random() * this.QUOTES.length);
        return this.QUOTES[randomIndex];
    }
}
