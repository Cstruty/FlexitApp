package com.example.flexit.quote_of_day;

public class Quote {

    private String body;
    private String author;

    public Quote() {
        this.author = "Albert Einstein";
        this.body = "Born too early to discuver everything, born too late to experince space travel, " +
                "but born in time to experience the dankest of memes";
    }

    protected void setBody(String body) {
        this.body = body;
    }

    protected void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return this.body;
    }


    public String getAuthor() {
        return this.author;
    }

    public boolean equals(Quote quote) {
        return (this.author.equals(quote.getAuthor()) &&
                this.body.equals(quote.getBody()));
    }
}
