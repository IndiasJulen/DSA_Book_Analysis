package maps;

import java.io.IOException;

public class Editor {
    public static void main(String[] args) throws IOException {
        String fileName1 = "books2/book1_analized.txt";
        String fileName2 = "books2/book2_analized.txt";
        String fileName3 = "books2/book3_analized.txt";
        String fileName4 = "books2/book4_analized.txt";

        Character c = 'h';

        // Create the books
        MapBook book1 = new MapBook(fileName1);
        MapBook book2 = new MapBook(fileName2);
        MapBook book3 = new MapBook(fileName3);
        MapBook book4 = new MapBook(fileName4);

        // Create the book collection and add the books into it
        BookCollection collection = new BookCollection();
        collection.addBook(book1);
        collection.addBook(book2);
        collection.addBook(book3);
        collection.addBook(book4);

        book1.print();

        // test of the printAmountLexicalItem method with the word "etorri" (come)
        collection.printAmountLexicalItem("etorri");

        // test of the writeGlossary method with the "h" character
        System.out.println("GLOSSARY OF THE 4th BOOK FOR THE " + c + " STARTING CHARACTER");
        System.out.println();
        book4.writeGlossary(c);
        System.out.println();

        // test of the writeByCategory method with a parameter
        System.out.println("LIBURU1 LIBURUAREN IZE LIB KATEGORIAREN LEMA-ZERRENDA:");
        System.out.println("LIST OF THE LEXICAL FORMS FOR THE \"IZE LIB\" CATEGORY IN THE 2nd BOOK:");
        book2.writeByCategory("IZE LIB");
        System.out.println();

        // test of the writeByCategory method without parameters
        System.out.println("ALL THE LEXICAL FORMS IN THE 2nd BOOK BY CATEGORY: ");
        book2.writeByCategory();

        // test of the getCategoryMostUsedLexicalItem method
        System.out.println("MOST USED WORD IN THE \"IOR ELK\" CATEGORY");
        System.out.println("1st BOOK:");
        System.out.println("\t" + book1.getCategoryMostUsedLexicalItem("IOR ELK").toString());
        System.out.println();

        System.out.println("MOST USED WORD IN THE \"IZE IZB\" CATEGORY");
        System.out.println("1st BOOK:");
        System.out.println("\t" + book1.getCategoryMostUsedLexicalItem("IZE IZB").toString());
        System.out.println();

        System.out.println("MOST USED WORD IN THE \"IZE IZB\" CATEGORY");
        System.out.println("2nd BOOK:");
        System.out.println("\t" + book2.getCategoryMostUsedLexicalItem("IZE IZB").toString());
        System.out.println();

        System.out.println("MOST USED WORD IN THE \"IZE IZB\" CATEGORY");
        System.out.println("3nrd BOOK:");
        System.out.println("\t" + book3.getCategoryMostUsedLexicalItem("IZE IZB").toString());
        System.out.println();

        System.out.println("MOST USED WORD IN THE \"IZE IZB\" CATEGORY");
        System.out.println("4th BOOK:");
        System.out.println("\t" + book4.getCategoryMostUsedLexicalItem("IZE IZB").toString());
        System.out.println();
    }

}
