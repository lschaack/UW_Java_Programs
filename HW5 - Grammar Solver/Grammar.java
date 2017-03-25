// Lucas Schaack
// 5/14/15
// CSE143
// Ta: Radu Cracut
// Section AS
//
/* Reads a text file using Backus-Naur Form syntax to describe a formal language, capable of
 * identifying non-terminals in the language and generating a random well-formed occurrence
 * of a given non-terminal. */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.Random;

public class Grammar {

   private Map<String, List<String>> grammar;
   private Random r;
   
   /* Initializes a new grammar object using a list of lines of a
    * text file following Backus-Naur Form.
    * Throws IllegalArgumentException if the given String is either null or empty. */
   public Grammar(List<String> rules) {
      if (rules == null || rules.isEmpty()) {
         throw new IllegalArgumentException("https://youtu.be/6I2pcIbyq-0");
      }
      
      this.grammar = new TreeMap<String, List<String>>();
      this.r = new Random();
      
      for (String line : rules) {
         // add the non-terminals as keys
         String[] lineArray= line.split("::=");
         String nonTerminal = lineArray[0];
         String ruleLine = lineArray[1];
         
         if (!this.grammar.containsKey(nonTerminal)) {
            this.grammar.put(nonTerminal, new ArrayList<String>());
         }
         
         String[] singleRules = ruleLine.split("\\|");
         // add the array to the corresponding List
         this.grammar.get(nonTerminal).addAll(Arrays.asList(singleRules));
      }
   }
   
   /* Returns true if the given String is a non-terminal found in the provided formal language.
    * Throws IllegalArgumentException if the given String is either null or empty. */
   public boolean isNonTerminal(String symbol) {
      if (symbol == null || symbol.isEmpty()) {
         throw new IllegalArgumentException("https://youtu.be/Si-jQeWSDKc");
      }
      
      return this.grammar.containsKey(symbol);
   }
   
   /* Returns a String representation of the grammar object. */
   public String toString() {
      return this.grammar.keySet().toString();
   }
   
   /* Returns a single random occurrence of the given symbol as a String.
    * Throws IllegalArgumentException if the given String is null or not a non-terminal. */
   public String getRandom(String nonTerminal) {
      if (nonTerminal == null || !this.isNonTerminal(nonTerminal)) {
         throw new IllegalArgumentException("http://ideas.ted.com/sebastiao-salgado-a-gallery-of-spectacular-photographs/");
      }
      
      String result = "";
      
      List<String> ruleSet = this.grammar.get(nonTerminal);
      // get a random rule from the list
      String ruleString = ruleSet.get(this.r.nextInt(ruleSet.size())).trim();
      String[] rule = ruleString.split("\\s+");
      
      for (int i = 0; i < rule.length; i++) {
         if (this.isNonTerminal(rule[i])) {
            result += this.getRandom(rule[i]);
         } else {
            result += rule[i] + " ";
         }
      }
      
      return result;
   }
   
   /* Returns a given number of random occurrences of the given symbol as a List of Strings. */
   public List<String> getRandom(int number, String nonTerminal) {
      List<String> results = new ArrayList<String>();
      
      for (int i = 0; i < number; i++) {
         results.add(this.getRandom(nonTerminal));
      }
      
      return results;
   }
}