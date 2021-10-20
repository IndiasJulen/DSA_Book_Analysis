package arrayLists;

import java.util.*;

import arrayLists.Book;

import java.lang.Math;

public class BookCollection {

	private ArrayList<Book> bookCollection;

	/**
	 * Class constructor: initializes an empty collection
	 */
	public BookCollection() {
		this.bookCollection = new ArrayList<Book>();
	}

	/**
	 * Adds a book into the collection
	 * @param book
	 */
	public void addBook(Book book) {
		this.bookCollection.add(book);
	}

	/**
	 * Returns the amount of books in the collection
	 * @return size of the Array List that contains all the books 
	 */
	public int getBookAmount() {
		return this.bookCollection.size();
	}

	/**
	 * Prints the most significant words of each book in the collection from the least to the most ones.
	 * Those who have the higher tf-idf value are the most significant words in the book.
	 *
	 * @param sigAmount: the amount of significant words we want to print. 10 will be the default value.
	 */
	public void printMostSignificantWords(int sigAmount) {
	    ArrayList<String> words = new ArrayList<String>();
	    ArrayList<Double> tfidf = new ArrayList<Double>();
	    int book = 0;
	    for (Book b : this.bookCollection) { // for all the books in the book collection
	        book++;
	        for (String w : b.getBook()) { // for all words in the book
	            if (!words.contains(w.toLowerCase())) { 
	                words.add(w.toLowerCase()); // if the word is not in the collection, add the word to the list
	                tfidf.add(tfIdf(b, w.toLowerCase())); // calculate the tf-idf value and add into the other list in the same position
	            }
	        }
	        for (int i = 0; i < tfidf.size() - 1; i++) { //sort them from smallest to largest using selection sort
	            for (int j = i + 1; j < tfidf.size(); j++) {
	                if (tfidf.get(i) < tfidf.get(j)) { 
	                    Collections.swap(words, i, j); // swap the word and the tfidf value in both lists
	                    Collections.swap(tfidf, i, j);
	                }
	            }
	        }
	        System.out.println("BOOK " + book + ": "); // Finally, print all
	        for (int i = 0; i <= sigAmount - 1; i++) { 
	            System.out.println(i + ". " + words.get(i) + "  TF-IDF: " + tfidf.get(i));
	        }
			System.out.println("************************************");
	        System.out.println();
	        words.clear();
	        tfidf.clear();
	    }
	}

	/**
	 * Returns the idf(Inverse Document Frequency) value of the term in the book collection
	 *
	 * @param term, of type String
	 * @return IDF value, of type double
	 */
	public double idf(String term) {
		double count = 0;
		for (int i = 0; i<this.bookCollection.size(); i++) {
			if(this.bookCollection.get(i).hasWord(term)) count++;
		}
		return Math.log((this.bookCollection.size())/(0.5+count));
	}

	/**
	 * Given a book and a term, calculates the tf-idf value of the term in the book
	 *
	 * @param book of type Book
	 * @param term of type String
	 *
	 * @return TFIDF value of type double
	 */
	public double tfIdf(Book book, String term) {
		return (book.tf(term)*idf(term));
	}
}
