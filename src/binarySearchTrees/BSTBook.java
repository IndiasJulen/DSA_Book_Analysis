package binarySearchTrees;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class BSTBook {
	private BSTBookNode root;
	private int size;
	private ArrayList<String> words;
	public ArrayList<Word> analizedText;

	/**
	 * Word class, containing the word and its tf-idf value
	 */
	private class Word{
		private String word;
		private double tfidf;

		/**
		 * Class constructor
		 * @param hitza of type String
		 * @param tfidf of type double
		 */
		public Word(String word, double tfidf) {
			this.word = word;
			this.tfidf = tfidf;
		}
	}


	/**
	 * Class constructor: initializes the tree from an input file
	 * @param fileName: a String containing the name of the file where the book is stored
	 * @throws IOException: in case an input-output exception occurs
	 */
	public BSTBook(String fileName) throws IOException {
		this.root = new BSTBookNode("n");
		this.words = new ArrayList<String>();
		this.analizedText = new ArrayList<Word>();
		
		File file = new File(fileName);
		FileReader input = new FileReader(file);
		BufferedReader reader = new BufferedReader(input);
		String line = reader.readLine();
		String[] words;
		while(line != null) {
			words = line.trim().split("[\\s,;:. ()\"]+");
			for(String elem : words) {
				if(!elem.equalsIgnoreCase("")) {
					size++;
					this.addWord(elem);
				}
			}
			line = reader.readLine();
		}
		reader.close();
	}
	
	
	
	public void quickSort() {
		quickSort(this.analizedText, 0, this.analizedText.size()-1);
	}

	/**
	 * Method to sort by using Quick Sort algorithm
	 * @param arr: of type ArrayList<Word> 
	 * @param lowPos: of type int
	 * @param upPos: of type int
	 */
	private void quickSort(ArrayList<Word> arr, int lowPos, int upPos) {
		if (upPos > lowPos) {
			int pivotPos = divide(arr, lowPos, upPos);
			quickSort(arr, lowPos, pivotPos - 1);
			quickSort(arr, pivotPos + 1, upPos);
		}
	}
	
	private int divide(ArrayList<Word> arr, int lowPos, int upPos) {
		int i = lowPos, j = upPos + 1;
		double pivot = arr.get(lowPos).tfidf;
		while (i < j) {
			j--;
			while (j > lowPos & (double)arr.get(j).tfidf < pivot) j--;
			i++;
			while (i < upPos &(double) arr.get(i).tfidf > pivot) i++;
			if (i < j) swap(arr, i, j);
		}
		swap(arr, lowPos, j);
		return j;
	}
	
	public void swap(ArrayList<Word> arr, int i, int j) {
		Word lag = arr.get(i);
		arr.set(i, arr.get(j));
		arr.set(j, lag);
	}


	/**
	 * Method to add a word that has been analized to the analized words list
	 * @param word: of type String
	 * @param tfidf: of type Double
	 */
	public void addAnalized(String word, Double tfidf) {
		Word word1 = new Word(word, tfidf);
		this.analizedText.add(word1);
	}

	/**
	 * Adds a word into the book
	 * @param hitza: of type String
	 */
	public void addWord(String hitza) {
		this.root.addWord(hitza);
	}

	/**
	 * Method to get the amount of words in the book
	 * @return this.size: of type int
	 */
	public int getWordAmount() {
		return this.size;
	}

	/**
	 * Method to get how many times a word appears in the book
	 * @param word: of type String
	 * @return amount of appearances
	 */
	public int appearanceAmount(String word) {
		return this.root.appearanceAmount(word);
	}

	/**
	 * Method to calculate the tf value of a word
	 * @param word: of type String
	 * @return tf value of type double
	 */
	public double tf(String word) {
		int count = this.root.appearanceAmount(word);
		return (double)count/(double)this.getWordAmount();
	}

	/**
	 * Method to know either a word is in the book or not
	 * @param word: the word we can search for of type String
	 * @return true if the word is in the book, false otherwise
	 */
	public boolean hasWord(String word) {
		return this.root.hasWord(word);
	}

	
	private void getWords() {
		this.root.getWords(this.words);
	}

	/**
	 * Method to get the words in the book
	 * @return an Array List of String containing all the words
	 */
	public ArrayList<String> getsWords(){
		this.getWords();
		return this.words;
	}

	/**
	 * Prints the most significant words of each book in the collection from the least to the most ones.
	 * Those who have the higher tf-idf value are the most significant words in the book.
	 *
	 * @param sigAmount: the amount of significant words we want to print. 10 will be the default value.
	 */
	public void printMostSignificantWords(int sigAmount) {
		for(int i = 0; i < sigAmount; i++) {
			System.out.println(this.analizedText.get(i).word + " = " + this.analizedText.get(i).tfidf);
		}
	}
}
