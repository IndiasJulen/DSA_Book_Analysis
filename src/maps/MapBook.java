package maps;

import java.io.*;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class MapBook {

    private TreeMap<String, ArrayList<String>> book;


    /**
     * Empty class constructor
     */
    public MapBook() {
    }

    /**
     * Non-empty class constructor: initializes the book
     * Reads the given file and builds a book using a TreeMap
     * The lines that don't contain 4 items won't be taken into account
     *
     * @param fileName: name of the input file
     * @throws IOException in case an input-output exception occurs
     */
    public MapBook(String fileName) throws IOException {
    	// Using a TreeMap keys will be stored according to alphabetical order and being case insensitive
        this.book = new TreeMap<String, ArrayList<String>>(String.CASE_INSENSITIVE_ORDER); 

        File file = new File(fileName);
        FileReader input = new FileReader(file);
        BufferedReader reader = new BufferedReader(input);
        String line = reader.readLine();
        String[] components;

        while (line != null) {
            components = line.trim().split("[ ]+");
            if (components.length == 4) { // take into account only 4-item lines
                String key = components[1] + " " + components[2] + " " + components[3]; // create the key
                if (!this.book.containsKey(key)) { // if the lexical item is not in the map
                    ArrayList<String> formList = new ArrayList<String>(); // create it's form list
                    formList.add(components[0]); // add the form into the list
                    this.book.put(key, formList); // map both the key and it's value, in this case the form list
                } else {
                    this.book.get(key).add(components[0]); // if the lexical item exists in the book just add the form into the list
                }
                this.book.get(key).sort(String::compareToIgnoreCase); // sort the map lexicographically 
            }
            line = reader.readLine();
        }
        reader.close();
    }

    /**
     * Prints the content of the book, each key (lexical item) followed by it's value (corresponding form list)
     */
    public void print() {
        System.out.println("******************** LIBURUAREN EDUKIA ********************");
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) {
            String key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            System.out.println("<" + key + ">: " + value.toString());
        }
    }

    /**
     * Given a lexical item returns the amount of times that item has been used
     *
     * @param lexItem: input lexical item
     * @return count: number of appearances of the item
     */
    public int countLexicalItem(String lexItem) {
        int count = 0;
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) {
            String[] key = entry.getKey().trim().split("[ ]+");
            ArrayList<String> value = entry.getValue();
            if (key[0].equalsIgnoreCase(lexItem)) {
            	count += value.size();
            }
        }
        return count;
    }

    /**
     * Given a form and a formList, returns the amount of appearances of the form in that list
     *
     * @param formList: an ArrayList of Strings
     * @param form: of type String
     * @return count: number of times of that form in the list
     */
    public int countForm(ArrayList<String> formList, String form) {
        int count = 0;
        for (String form1 : formList) {
            if (form1.equalsIgnoreCase(form)) count++;
        }
        return count;
    }

    /**
     * Override of the contains method, which is case insensitive
     *
     * @param formList: an ArrayList of Strings
     * @param form: of type String
     * @return true if the list contains the given form, false otherwise
     */
    public boolean contains(ArrayList<String> list, String form) {
        for (String form1 : list) {
            if (form1.equalsIgnoreCase(form)) return true;
        }
        return false;
    }

    /**
     * Liburu-sortako liburu guztientzat, emandako lema batek zein erabilera izan
     * duen idatziko da irteera estandarrean
     * Given a lexical item, prints it's amount of appearances and all forms' in lexicographical order
     * 
     * @param lexItem: of type String
     */
    public void printAmountLexicalItem(String lexItem) {
        int count = 0;
        // in this list we'll be adding forms each time we count them, in order to take them only once into account
        ArrayList<String> counted = new ArrayList<String>(); 
        System.out.println("Appearances: " + countLexicalItem(lexItem));
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) {
            String[] key = entry.getKey().trim().split("[ ]+"); // get the key
            ArrayList<String> value = entry.getValue(); // get it's value (form list)
            if (key[0].equalsIgnoreCase(lexItem)) { // if we find the input lexical item
                for (String form : value) { // iterate all the form list
                    if (!contains(counted, form)) { // if we didn't take into account the form
                        count = countForm(value, form); // get the amount
                        counted.add(form); // add the form into the list
                        System.out.println("\t" + form + ": " + count); // print the info
                    }
                }
            }
        }
    }

    /**
     * Given a character prints all the info related to all the lexical items starting with that character
     * 
     * The info will be display in this format:
     * Lexical form: <amount of appearances of the form>
     * \t 	form <amount of appearances of the form>
     *
     * All the info will be displayed in lexicographical order.
     */
    public void writeGlossary(Character ch) {
        ArrayList<String> countedLexicalItems = new ArrayList<String>();  // a list not to take into account the same lex item more than once
        String c = ch.toString();
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) { 
            String[] key = entry.getKey().trim().split("[ ]+"); 
            if (key[0].toLowerCase().startsWith(c) && !contains(countedLexicalItems, key[0])) {
                System.out.println("------ " + key[0].toUpperCase() + " ------");
                printAmountLexicalItem(key[0]);
                System.out.println();
                countedLexicalItems.add(key[0]);
            }
        }
    }

    /**
     * Given a category, prints all the lexical forms related to this 
     * category in the book
     *
     * The info will be display in this format:
     * Category
     * \t	lex form <amount of appearances of the form>
     *
     * All the info will be displayed in lexicographical order.
     */
    public void writeByCategory(String category) {
        String[] cat = category.trim().split("[ ]+");
        System.out.println(category + ":");
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) { 
            String[] key = entry.getKey().trim().split("[ ]+"); 
            ArrayList<String> value = entry.getValue(); 
            if (key[1].equals(cat[0]) && key[2].equals(cat[1])) { 
                System.out.println("\t" + key[0] + ": " + value.size()); // print lexical form + count
            }
        }
    }

    /**
     * Prints all the grammatical categories in the book and each category's lexical forms
     *
     * The info will be display in this format:
     * Category
     * \t	lex form <amount of appearances of the form>
     *
     * All the info will be displayed in lexicographical order.
     */
    public void writeByCategory() {
        ArrayList<String> catList = new ArrayList<String>();
        String cat;
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) { 
            String[] key = entry.getKey().trim().split("[ ]+"); 
            cat = key[1] + " " + key[2]; // store the category and subcategory in a string separated by an space
            if(!catList.contains(cat)) {
               catList.add(cat);
            }
        }
        catList.sort(String::compareToIgnoreCase); // sort in lexicographical order

        for(String kategoria : catList) { 
        	writeByCategory(kategoria); 
            System.out.println();
        }
    }

    /**
     * Given a category returns the most used lexical form and how many times
     * has been used in the book
     *
     * @param category: of type String
     * @return lexItemMax: object of the LexItemCount class which stores the lexical form and he count
     */
    public LexItemCount getCategoryMostUsedLexicalItem(String category) {
        String[] cat = category.trim().split("[ ]+");
        LexItemCount lexItemCount = new LexItemCount();
        LexItemCount lexItemMax = new LexItemCount();
        for (Map.Entry<String, ArrayList<String>> entry : this.book.entrySet()) {
            String[] key = entry.getKey().trim().split("[ ]+");
            ArrayList<String> value = entry.getValue(); 
            if(key[1].equals(cat[0]) && key[2].equals(cat[1])) {
            	lexItemCount.initializeLexItem(key[0], value.size());
                if (lexItemCount.getCount() >= lexItemMax.getCount()) {
                	lexItemMax.initializeLexItem(lexItemCount.getLexItem(), lexItemCount.getCount());
                }
            }
        }
        return lexItemMax;
    }
}
