package arrayLists;
import java.util.Scanner;

public class Editor {
    public static void main(String[] args) throws java.io.IOException {
        int sigAmount = 10;

        // Create file names
        String fileName1 = "books/book1.txt";
        String fileName2 = "books/book2.txt";
        String fileName3 = "books/book3.txt";
        String fileName4 = "books/book4.txt";
        String fileName5 = "books/book5.txt";

        // Create books from files
        System.out.println("CREATE BOOK FROM A FILE");
        Book book1 = new Book(fileName1);
        System.out.println("Number of words in the 1st book: " + book1.getWordAmount());
        System.out.println("");

        System.out.println("CREATE BOOK FROM A FILE");
        Book book2 = new Book(fileName2);
        System.out.println("Number of words in the 2nd book: " + book2.getWordAmount());
        System.out.println("");
      
        System.out.println("CREATE BOOK FROM A FILE");
        Book book3 = new Book(fileName3);
        System.out.println("Number of words in the 3rd book: " + book3.getWordAmount());
        System.out.println("");
 
        System.out.println("CREATE BOOK FROM A FILE");
        Book book4 = new Book(fileName4);
        System.out.println("Number of words in the 4th book: " + book4.getWordAmount());
        System.out.println("");

        System.out.println("CREATE BOOK FROM A FILE");
        Book book5 = new Book(fileName5);
        System.out.println("Number of words in the 5th book: " + book5.getWordAmount());
        System.out.println("");

        
        // Create book collection
        BookCollection collection = new BookCollection();

        // Add books into our collection
        collection.addBook(book1);
        collection.addBook(book2);
        collection.addBook(book3);
        collection.addBook(book4);
        collection.addBook(book5);

        
        // Most relevant words from all books
        System.out.println("MOST " + sigAmount + " RELEVANT WORDS FROM ALL " + collection.getBookAmount() + " BOOKS:");
        System.out.println("************************************");
        collection.printMostSignificantWords(sigAmount);


        /// Print the next most used word
        Scanner keyboard = new Scanner(System.in); // Keyboard for the input word
        while(true) {
            System.out.println("WRITE A WORD FOR THE NEXT MOST USED WORD (Empty line to exit): ");
            String mostUsed = keyboard.nextLine();
            if(mostUsed.equals("")) {
                System.out.println("Finishing...");
                break;
            }
            System.out.println("1st BOOK: " + book1.nextMostUsedWord(mostUsed));
            System.out.println("2nd BOOK: " + book2.nextMostUsedWord(mostUsed));
            System.out.println("3rd BOOK: " + book3.nextMostUsedWord(mostUsed));
            System.out.println("4th BOOK: " + book4.nextMostUsedWord(mostUsed));
            System.out.println("5th BOOK: " + book5.nextMostUsedWord(mostUsed));
            System.out.println("");
        }
        keyboard.close();
    }
}