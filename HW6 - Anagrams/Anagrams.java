// Lucas Schaack
// 5/18/15
// CSE143
// Ta: Radu Cracut
// Section AS
// Helper methods are beneath all public methods
//
/* Reads a dictionary to create an object capable of printing every combination of words
 * that is an anagram of a given string, using the provided dictionary. */

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Anagrams {
   
   private List<String> dictionary;
   private Map<String, LetterInventory> inventories;
   
   /* Initializes a new anagrams object using a dictionary in the form of a list. */
   public Anagrams(List<String> dictionary) {
      this.dictionary = dictionary;
      this.inventories = new HashMap<String, LetterInventory>();
      
      for (String word : this.dictionary) {
         this.inventories.put(word, new LetterInventory(word));
      }
   }
   
   /* Prints every anagram of the given string using the words in the class instance's
    * dictionary, each anagram containing up to the given maximum number of words, or
    * no limit if max is set to zero.
    * Throws IllegalArgumentException if the given max value is less than zero. */
   public void print(String s, int max) {
      if (max < 0) {
         throw new IllegalArgumentException("http://www.specialtybottle.com/");
      }
   
      List<String> dict = this.dictionary;
      List<String> chosenWords = new ArrayList<String>(); // accumulator
      LetterInventory letters = new LetterInventory(s);
      print(letters, max, dict, chosenWords);
   }
   
   /* Prints every anagram of the given LetterInventory using the words in the provided
    * dictionary, each anagram containing up to the given maximum number of words, or no
    * limit if max is set to zero. Helper method for public print method. */
   private void print(LetterInventory letters, int max, List<String> dict, List<String> chosenWords) {
      List<String> prunedDict = this.pruneDict(letters, dict);
      // base case
      if (letters.isEmpty()) {
         if (max == 0 || chosenWords.size() <= max) {
            System.out.println(chosenWords);
         }
      }
      
      LetterInventory currChoice;
      
      for (String word : prunedDict) {
         currChoice = this.inventories.get(word);
         // choose
         chosenWords.add(word);
         letters = letters.subtract(currChoice);
         // explore
         this.print(letters, max, prunedDict, chosenWords);
         // unchoose
         chosenWords.remove(chosenWords.size() - 1);
         letters = letters.add(currChoice);
      }
   }
   
   /* Returns a pruned dictionary in the form of a list containing only words from the
    * original dictionary which contain the same letters found in the given LetterInventory. */
   private List<String> pruneDict(LetterInventory letters, List<String> dict) {
      LetterInventory temp;
      List<String> newDict = new ArrayList<String>();
      
      for (String word : dict) {
         temp = this.inventories.get(word);
         
         if (letters.subtract(temp) != null) {
            newDict.add(word);
         }
      }
      
      return newDict;
   }
}