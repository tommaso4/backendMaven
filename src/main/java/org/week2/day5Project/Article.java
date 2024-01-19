package org.week2.day5Project;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Article {
    private Long isbn;
    private String titolo;
    private int anno;

    private int numPages;

    static List<Long> isbnList = new ArrayList<>();

    public Article(String titolo, int anno, int numPages) throws NoMoreSpaceException {
        this.titolo = titolo;
        this.anno = anno;
        this.numPages = numPages;
        this.isbn = setIsbn();
    }

    public Long setIsbn() throws NoMoreSpaceException {
        Random random = new Random();
        Long possibleIsbn;
        final long maxSpace = 1000L;

        if (isbnList.size() < maxSpace) {
            do {
                possibleIsbn = (long) random.nextInt(1000)+1;
            } while (isbnList.contains(possibleIsbn));

            isbnList.add(possibleIsbn);
            return possibleIsbn;
        } else {
            throw new NoMoreSpaceException("No more space in archive");
        }
    }


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public void setNumPages(int numPages) {
        this.numPages = numPages;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnno() {
        return anno;
    }

    public int getNumPages() {
        return numPages;
    }
}
