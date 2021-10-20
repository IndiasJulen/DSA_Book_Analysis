package binarySearchTrees;

import java.util.ArrayList;

public class BSTBookCollection {
	// We will use an Array List of BSTs to store books
	ArrayList<BSTBook> bookCollection;

	/**
	 * Class empty constructor
	 */
	public BSTBookCollection() {
		this.bookCollection = new ArrayList<BSTBook>();
	}

	/**
	 * Method to add a book into the collection
	 * @param book: the book we want to add
	 */
	public void addBook(BSTBook book) {
		this.bookCollection.add(book);
	}

	/**
	 * Method to calculate the idf value of a word
	 * @param word: of type String
	 * @return idf value: of type double
	 */
	public double idf(String word) {
		int count = 0;
		for(BSTBook book : this.bookCollection) {
			if(book.hasWord(word)) count++;
		}
		return Math.log((double)this.bookCollection.size()/((double)count + 0.5));
	}

	/**
	 * Method to calculate the tf-idf value of a word
	 * @param book: the book in where the word appears
	 * @param word: of type String
	 * @return tf-idf value: of type double
	 */
	public double tfIdf(BSTBook book, String word) {
		return book.tf(word)*this.idf(word);
	}

	/**
	 * Prints the most significant words of each book in the collection from the least to the most ones.
	 * Those who have the higher tf-idf value are the most significant words in the book.
	 *
	 * @param sigAmount: the amount of significant words we want to print. 10 will be the default value.
	 */
	public void printMostSignificantWords(int sigAmount) {
		double tfIdf;
		int i = 1;
		ArrayList<String> words;
		for(BSTBook book : this.bookCollection) {
			words = book.getsWords();
			for(String term : words) {
				tfIdf = this.tfIdf(book, term);
				book.addAnalized(term, tfIdf);
			}
			book.quickSort();
			System.out.println("************************************");
			System.out.println("BOOK " + i + ": ");
			book.printMostSignificantWords(sigAmount);
			i++;
		}
	}
}
