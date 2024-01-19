package org.week2.day5Project;

public class Magazine extends Article{
    private Periodicity periodicity;

    public Magazine(String titolo, int anno, int numPages, Periodicity periodicity) throws NoMoreSpaceException {
        super(titolo, anno, numPages);
        this.periodicity = periodicity;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "isbn=" + getIsbn() +
                " @ " +" titolo=" + getTitolo() +
                " @ " +" anno=" + getAnno() +
                " @ " +" numPages=" + getNumPages() +
                " @ " +" periodicity=" + periodicity +
                '}';
    }
}
