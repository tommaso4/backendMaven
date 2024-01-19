package org.week2.day5Project;

public class Book extends Article{

    private String Author;
    private String Genres;

    public Book(String titolo, int anno, int numPages, String author, String genres) throws NoMoreSpaceException {
        super(titolo, anno, numPages);
        Author = author;
        Genres = genres;
    }

    public String getAuthor() {
        return Author;
    }

    public String getGenres() {
        return Genres;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setGenres(String genres) {
        Genres = genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + getIsbn() +
                " @ "+" titolo=" + getTitolo() +
                " @ "+" anno=" + getAnno() +
                " @ "+" numPages=" + getNumPages() +
                " @ "+" Author=" + Author +
                " @ "+" Genres=" + Genres +
                '}';
    }

}
