package org.week2.day4_Streem2File;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String frase = "\nprima riga file\n";

        File file = new File("filepath/file.txt");

        try {
              FileUtils.writeStringToFile(file, frase, Charset.defaultCharset(), true);
//            String s = FileUtils.readFileToString(file,Charset.defaultCharset());
//            System.out.println(s);
//            FileUtils.deleteQuietly(file);
//            FileUtils.deleteDirectory(new File("filepath"));

            //crea un path
            FileUtils.createParentDirectories(file);
            File file2 = new File("filepath");
            System.out.println(file2.length());
            System.out.println(file2.getAbsolutePath());
            System.out.println("-------------------");
            Arrays.stream(file2.list()).forEach(s -> System.out.println(s));
            System.out.println("-------------------");
            List<String> lista = FileUtils.readLines(file,Charset.defaultCharset());
            System.out.println(lista);
        }catch (IOException e){
            e.getMessage();
        }

    }
}