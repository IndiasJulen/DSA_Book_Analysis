package binarySearchTrees;

import java.util.ArrayList;

public class BSTBookNode {
	private String word;
	// if a word appears more than once, we will just increment the "count" attribute, we wont add a new node
	private int count;
	private BSTBookNode left;
	private BSTBookNode right;

	/**
	 * Class constructor: introduces a word into the node and initializes the count to 1
	 * @param word, the word that is going to be stored in the node
	 */
	public BSTBookNode(String word) {
		this.word = word;
		this.count = 1; 
	}

	/**
	 * @return true if the node has left child, false otherwise
	 */
	public boolean hasLeft() {
		return this.left != null;
	}

	/**
	 * @return true if the node has right child, false otherwise
	 */
	public boolean hasRight() {
		return this.right != null;
	}


	/**
	 * Method to create a new node when we add a new word
	 * @param word: the word that's going to be added
	 */
	public void addWord(String word) {
		if(this.word.compareToIgnoreCase(word)>=0) {
			if(this.hasRight() && this.right.word.equalsIgnoreCase(word)) this.right.count++;
			else if(this.hasRight()) this.right.addWord(word);
			else this.right = new BSTBookNode(word);
		} else {
			if(this.hasLeft() && this.left.word.equalsIgnoreCase(word)) this.left.count++;
			else if(this.hasLeft()) this.left.addWord(word);
			else this.left = new BSTBookNode(word);
		}
	}

	/**
	 * Returns the amount of times the word appears in the book
	 * @param word of type String
	 * @return the "count" attribute of the node that stores that word
	 */
	public int appearanceAmount(String word) {
		if(this.word.equalsIgnoreCase(word)) return this.count;
		else if(this.word.compareToIgnoreCase(word)>=0 && this.hasRight()) return this.right.appearanceAmount(word);
		else if(this.word.compareToIgnoreCase(word)<0 && this.hasLeft()) return this.left.appearanceAmount(word);
		return 0;
	}

	/**
	 * Method to know if a word is in the book or not
	 * @param word of type String
	 * @return true if the word is in the book, false otherwise
	 */
	public boolean hasWord(String word) {
		if(this.word.equalsIgnoreCase(word)) return true;
		else if(this.word.compareToIgnoreCase(word)>=0) {
			if(this.hasRight()) return this.right.hasWord(word);
			return false;
		}
		else {
			if(this.hasLeft()) return this.left.hasWord(word);
			return false;
		}
	}

	/**
	 * Given an array list as a parameter, stores the all words in it
	 * @param arr, of type ArrayList<String> 
	 */
	public void getWords(ArrayList<String> arr) {
		if(this.hasLeft()) this.left.getWords(arr);
		arr.add(this.word);
		if(this.hasRight()) this.right.getWords(arr);
	}
}
