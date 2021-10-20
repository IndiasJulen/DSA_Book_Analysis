package binarySearchTrees;

import java.io.IOException;

public class Editor {

	public static void main(String[] args) throws IOException {
		int sigAmount = 10;

        // Create file names
        String fileName1 = "books/book1.txt";
        String fileName2 = "books/book2.txt";
        String fileName3 = "books/book3.txt";
        String fileName4 = "books/book4.txt";
        String fileName5 = "books/book5.txt";

        // Create books from files
        System.out.println("CREATE BOOK FROM A FILE");
        BSTBook book1 = new BSTBook(fileName1);
        System.out.println("Number of words in the 1st book: " + book1.getWordAmount());
        System.out.println("");

        System.out.println("CREATE BOOK FROM A FILE");
        BSTBook book2 = new BSTBook(fileName2);
        System.out.println("Number of words in the 2nd book: " + book2.getWordAmount());
        System.out.println("");
      
        System.out.println("CREATE BOOK FROM A FILE");
        BSTBook book3 = new BSTBook(fileName3);
        System.out.println("Number of words in the 3rd book: " + book3.getWordAmount());
        System.out.println("");
 
        System.out.println("CREATE BOOK FROM A FILE");
        BSTBook book4 = new BSTBook(fileName4);
        System.out.println("Number of words in the 4th book: " + book4.getWordAmount());
        System.out.println("");

        System.out.println("CREATE BOOK FROM A FILE");
        BSTBook book5 = new BSTBook(fileName5);
        System.out.println("Number of words in the 5th book: " + book5.getWordAmount());
        System.out.println("");

		// Create book collection
		BSTBookCollection collection = new BSTBookCollection();

		// Add books into our collection
		collection.addBook(book1);
		collection.addBook(book2);
		collection.addBook(book3);
		collection.addBook(book4);
		collection.addBook(book5);

		// Most relevant words from all books
        System.out.println("MOST " + sigAmount + " RELEVANT WORDS FROM ALL " + collection.bookCollection.size() + " BOOKS:");
        collection.printMostSignificantWords(sigAmount);
        System.out.println("************************************");
        
	}

}
