package org.week2.day5Project;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Archive {
    static final Logger logger = LoggerFactory.getLogger("project");
    public static void main(String[] args) throws NoMoreSpaceException{

        List<Article> arichive1 = new ArrayList<>();
        Book book1 = new Book("Shantaram", 1998, 1170, "G.G.David", "Biografico");
        Book book2 = new Book("Shantaram2", 2000, 1170, "G.G.David", "Biografico");
        Book book3 = new Book("Un Uomo", 1978, 870, "O.Fallaci", "Biografico");
        Magazine magazine1 = new Magazine("SurfLine", 2020, 90, Periodicity.MONTHLY);
        Magazine magazine2 = new Magazine("Veggie", 2021, 90, Periodicity.WEEKLY);
        Magazine magazine3 = new Magazine("Veggie3", 2020, 90, Periodicity.WEEKLY);

        //first methods
        try {
            addArticle(arichive1, book1);
            addArticle(arichive1, book2);
            addArticle(arichive1, book3);
            addArticle(arichive1, magazine1);
            addArticle(arichive1, magazine2);
            addArticle(arichive1, magazine3);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        arichive1.forEach(article -> System.out.println(article));
        //Second methods

        Long isbnToDelate = book3.getIsbn();
        try {
            deleteArticle(arichive1, isbnToDelate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("-----------------Es2");
        arichive1.forEach(article -> System.out.println(article));

        //third
        System.out.println("-------------------es3");
        try {
            List<Article> listForYear = findForYear(arichive1, 2020);
            listForYear.forEach(article -> System.out.println(article));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        //fourth

        System.out.println("-------------------es4");
        try {
            List<Book> listForAuthor = findForAuthor(arichive1, "G.G.David");
            listForAuthor.forEach(article -> System.out.println(article));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //fifth
        try {
            addToFile(arichive1);
        } catch (IOException ex) {
            ex.getMessage();
        }

        //sixth
        System.out.println("----------------es6");

        try {
            List<Article> archiveFromFile = getFlie();
            archiveFromFile.forEach(article -> System.out.println(article));
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void addArticle(List<Article> list, Article article) throws Exception {
        if (!list.contains(article)) {
            list.add(article);
        } else {
            throw new Exception("Article is already inside this archive");
        }
    }

    public static void deleteArticle(List<Article> list, Long isbn) throws Exception {

        Article articleToDelete = list.stream().filter(article -> isbn.equals(article.getIsbn()))
                .findFirst().orElse(null);

        if (articleToDelete != null) {
            list.remove(articleToDelete);
        } else {
            throw new Exception("Article isn't in this list");
        }
    }

    public static List<Article> findForYear(List<Article> list, int year) throws Exception {
        List<Article> list1ArticlesForYear = list.stream().filter(article -> article.getAnno() == year).collect(Collectors.toList());
        if (list1ArticlesForYear.isEmpty()) {
            throw new Exception("No magazine for this year");
        }
        return list1ArticlesForYear;
    }

    public static List<Book> findForAuthor(List<Article> list, String author) throws Exception {

        List<Book> listForAuthor = list.stream().filter(article -> article instanceof Book)
                .map(article -> (Book) article).filter(book -> book.getAuthor().equals(author))
                .collect(Collectors.toList());
        if (listForAuthor.isEmpty()) {
            throw new Exception("This Author isn't presents in this archive");
        } else {
            return listForAuthor;
        }
    }

    public static void addToFile(List<Article> list) throws IOException {
        File fileDay5 = new File("filepath/day5.txt");
        String strFile = "";
        for (Article art : list) {
            strFile += art.toString() + "\n";
        }
        try {
            FileUtils.writeStringToFile(fileDay5, strFile, Charset.defaultCharset(), false);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static List<Article> getFlie() throws IOException, NoMoreSpaceException {

        File file = new File("filepath/day5.txt");
        String readFile = FileUtils.readFileToString(file, Charset.defaultCharset());

        String[] arrString = readFile.split("\n");
        List<Article> archive = new ArrayList<>();

        for (String str : arrString) {

            if (str.startsWith("Book")) {
                archive.add(generateBook(str));
            } else if (str.startsWith("Magazine")) {
                archive.add(generateMagazine(str));
            }
        }
        return archive;
    }

    public static Book generateBook(String string) throws NoMoreSpaceException {

        try {
            string = string.replaceAll("}", "");
            string = string.replaceAll("Book\\{", "");
            String[] arr = string.split("@");
            Map<String, String> valueMap = new HashMap<>();

            for (String strA : arr) {
                String[] keyValue = strA.trim().split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    valueMap.put(key, value);
                }
            }

            Book bookNew = new Book(
                    valueMap.get("titolo"),
                    Integer.parseInt(valueMap.get("anno")),
                    Integer.parseInt(valueMap.get("numPages")),
                    valueMap.get("Author"),
                    valueMap.get("Genres"));
            return bookNew;
        } catch (NoMoreSpaceException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public static Magazine generateMagazine(String string) throws NoMoreSpaceException {

        try {
            string = string.replaceAll("}", "");
            string = string.replaceAll("Magazine\\{", "");
            String[] arr = string.split("@");
            Map<String, String> valueMap = new HashMap<>();

            for (String strA : arr) {
                String[] keyValue = strA.trim().split("=");
                if (keyValue.length == 2) {
                    String key = keyValue[0];
                    String value = keyValue[1];
                    valueMap.put(key, value);
                }
            }

            Magazine magazineNew = new Magazine(
                    valueMap.get("titolo"),
                    Integer.parseInt(valueMap.get("anno")),
                    Integer.parseInt(valueMap.get("numPages")),
                    Periodicity.valueOf(valueMap.get("periodicity").toUpperCase()));

            return magazineNew;
        } catch (NoMoreSpaceException ex) {
            ex.printStackTrace();
            return null;
        }

    }

}
