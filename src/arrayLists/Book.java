package arrayLists;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public class Book {

	private ArrayList<String> book;

	/**
	 * Class constructor: creates the book structure
	 */
	public Book() {
		this.book = new ArrayList<String>();
	}

	/**
	 * Non empty constructor: creates a book from a given input file
	 * @param fileName: file name for the input file
	 * @throws java.io.IOException in case an input-output exception occurs 
	 */
	public Book(String fileName) throws java.io.IOException {
		this.book = new ArrayList<String>();
		FileReader input = new FileReader(fileName);
		BufferedReader reader = new BufferedReader(input);
		String line = reader.readLine();
		while (line != null) {
			String[] words = line.trim().split("[\\s,;:.\"]+"); // store all the words in the line into an string
			Collections.addAll(this.book, words); // add words into the book
			line = reader.readLine();
		}
		reader.close();
	}

	/**
	 * Method to get the amount of words in the book
	 * @return size of the Array List in where the book is stored
	 */
	public int getWordAmount() {
		return this.book.size();
	}

	/**
	 * See if a given word is in the book or not
	 * @param word, the word we want to search for
	 * @return true if the word is in the book, false otherwise
	 */
	public boolean hasWord(String word) {
		for(String w : this.book) {
			if(w.equalsIgnoreCase(word)) return true;
		}
		return false;
	}


	/**
	 * Given a word in the book, returns the most next used word in the book
	 * @param h: input word
	 * @return word: h's next most used word
	 */
	public String nextMostUsedWord (String w) {
		String word = null;
		String next = null;
		int i = 0;
		int count = 0;
		int max = 0;
		if (this.hasWord(w)) { // if the book has the given input word
			while (i<this.book.size()-1) { 
				if (this.book.get(i).equalsIgnoreCase(w)) { // check if each iteration's word equals the input word
					next = this.book.get(i+1); // store the next word
					for (int j = i; j<this.book.size(); j++) { // traverse all the book againd searching for the "next" word
						if((this.book.get(j).equalsIgnoreCase(w)) && (this.book.get(j+1).equalsIgnoreCase(next))) count++; // increment count each time we find the word
					}
					if(count > max) { // count and max and if needed update variables
						max = count;
						word = next;
					}
					count = 0;
				}
				i++; 
			}
		}
		return word;
	}

	/**
	 * Method to calculate the tf (Term Frecuency) value the term has in the book
	 * @param term, of type String type
	 * @return TF value, of type double
	 */
	public double tf(String term) {
		double count = 0;
		for (String s: this.book) {
			if (term.equalsIgnoreCase(s)) count++;
		}
		return count/getWordAmount();
	}

	/**
	 * Book's getter
	 * @return this.lib of Book type
	 */
	public ArrayList<String> getBook() {
		return this.book;
	}



}
