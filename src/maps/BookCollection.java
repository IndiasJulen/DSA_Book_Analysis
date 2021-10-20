package maps;

import java.util.ArrayList;

public class BookCollection {
    private ArrayList<MapBook> bookCollection;

    /**
     * Empty class constructor
     */
    public BookCollection() {
        this.bookCollection = new ArrayList<MapBook>();
    }

    /**
     * Adds a book into the collection
     * @param book: of type MapBook
     */
    public void addBook(MapBook book) {
        this.bookCollection.add(book);
    }

    /**
     * Prints the usage of a given lexical form in all the books in the collection
     *
     * Displayed info:
     * - Amount of appearances of the lexical form
     * - All the forms of the lexical form with the amount of appearances
     * 
     * All the info will be displayed in lexicographical order
     */
    public void printAmountLexicalItem(String lexForm) {
        for(int i = 0; i<this.bookCollection.size(); i++) {
            System.out.println("USAGE OF THE WORD " + "'" + lexForm + "'" + " IN THE BOOK " + i++ + ":");
            this.bookCollection.get(i).printAmountLexicalItem(lexForm);
            System.out.println();
        }
    }
}
