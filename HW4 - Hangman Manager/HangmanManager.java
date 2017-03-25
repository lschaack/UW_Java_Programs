// Lucas Schaack
// 4/30/15
// CSE143
// Ta: Radu Cracut
// Section AS
// Helper methods are beneath all public methods
//
/** Manages a cheating game of Hangman in which the answer of a given length is not decided
  * upon until no words out the provided dictionary can be constructed with the unguessed
  * letters of the alphabet. Capable of providing the pattern of decided letters in the
  * unchosen answer at any turn in the game. */

import java.util.*;

public class HangmanManager {
   
   private Set<String> words;
   private Set<Character> guesses;
   private int numGuesses;
   private String pattern;
   private final int NUM_LETTERS;
   
   /** Initializes a new manager using a dictionary list of possible words, the desired
     * length of the answer, and the maximum allowed number of guesses.
     * Throws IllegalArgumentException if passed a word length of less than one
     * or a max guess number of less that zero. */
   public HangmanManager(List<String> dictionary, int length, int max) {
      if (length < 1 || max < 0) {
         throw new IllegalArgumentException("http://i.imgur.com/5nj84Jv.jpg");
      }
      
      this.words = new TreeSet<String>();
      this.guesses = new TreeSet<Character>();
      this.numGuesses = max;
      this.pattern = "";
      this.NUM_LETTERS = length;
      
      for (int i = 0; i < dictionary.size(); i++) {
         if (dictionary.get(i).length() == this.NUM_LETTERS) {
            this.words.add(dictionary.get(i));
         }
      }
      
      for (int i = 0; i < this.NUM_LETTERS; i++) {
         this.pattern += "-";
      }
   }
   
   /** Returns the set of all words being considered. */
   public Set<String> words() {
      return this.words;
   }
   
   /** Returns the number of guesses remaining in the game. */
   public int guessesLeft() {
      return this.numGuesses;
   }
   
   /** Returns the set of characters that have already been guessed. */
   public Set<Character> guesses() {
      return this.guesses;
   }
   
   /** Returns the pattern of known characters in the word set being considered
     * by showing known characters and displaying unknown characters as a dash (-).
     * Throws an IllegalStateException if the set of words being considered is empty. */
   public String pattern() {
      if (this.words.size() == 0) {
         throw new IllegalStateException("http://irl.cs.ucla.edu/papers/right-size.html");
      }
      // pattern is updated in record method
      return this.pattern;
   }
   
   /** Records a guess by updating the number of guesses left, the set of words, and the
     * current pattern. Returns number of times the guessed character is found in the answer.
     * If two word sets are equally valid (each contain the same number of possible words),
     * the first set that is encountered will be chosen.
     * Throws an IllegalStateException if there are no guesses left.
     * Throws an IllegalArgumentException if the list of words is nonempty and the character
     * has already been guessed. */
   public int record(char guess) {
      if (numGuesses < 1) {
         throw new IllegalStateException("http://baseball.physics.illinois.edu/KBall/dickey-rhymes-hd.gif");
      } else if (!this.words.isEmpty() && this.guesses.contains(guess)) {
         throw new IllegalArgumentException("http://i.imgur.com/WR4Z8Bu.gifv");
      }
      
      this.guesses.add(guess);
      // compose map of word families from the guess
      Map<String, Set<String>> wordFams = this.populateMap(guess);
      Set<String> largestSet = new TreeSet<String>();
      String largestSetKey = "";
      
      for (String key : wordFams.keySet()) {
         // finds the largest set of words in the map of word families
         if (wordFams.get(key).size() > largestSet.size()) {
            largestSet = wordFams.get(key);
            largestSetKey = key;
         }
      }
      // set the set of considered words to the word family of the largest size
      this.words = largestSet;
      // add the new word family characteristic to the pattern
      this.addToPattern(largestSetKey);
      // uses the key of the largest set to determine how many of the guess char
      // is in the chosen family
      int lettersCorrect = this.countLetters(largestSetKey);
      
      if (lettersCorrect == 0) {
         this.numGuesses--;
      }
      
      return lettersCorrect;
   }
   
   /** Returns a populated map of word sets organized by the pattern of the location
     * of the given character in each word. */
   private Map<String, Set<String>> populateMap(char guess) {
      Map<String, Set<String>> wordFams = new TreeMap<String, Set<String>>();
      String family;
      
      for (String word : this.words) {
         // determine what family the word belongs to by constructing a pattern
         family = this.toPattern(word, guess);
         // create family if not created or add it to the set value if it has
         if (!wordFams.containsKey(family)) {
            wordFams.put(family, new TreeSet<String>());
         }
         // add the word to the set of words corresponding to its family
         wordFams.get(family).add(word);
      }
      
      return wordFams;
   }
   
   /** Returns the pattern of a word by showing characters corresponding to the guess as the
     * character and all other characters as a dash ('-'). */
   private String toPattern(String word, char guess) {
      String result = "";
      
      for (int i = 0; i < word.length(); i++) {
         if (word.charAt(i) == guess) {
            result += guess;
         } else {
            result += '-';
         }
      }
      
      return result;
   }
   
   /** Adds one pattern to another by replacing dashes in the previous pattern with
     * the known characters in the given string. */
   private void addToPattern(String toAdd) {
      for (int i = 0; i < this.NUM_LETTERS; i++) {
         // if the pattern being added has a character where the current pattern does not
         if (this.pattern.charAt(i) == '-' && toAdd.charAt(i) != '-') {
            // add the letter of the pattern being added to the current pattern
            this.pattern = this.pattern.substring(0, i) + toAdd.charAt(i)
               + this.pattern.substring(i + 1);
         }
      }
   }
   
   /** Returns the number of non-dash ('-') characters given a pattern. */
   private int countLetters (String pattern) {
      int count = 0;
      
      for (int i = 0; i < this.NUM_LETTERS; i++) {
         if (pattern.charAt(i) != '-') {
            count++;
         }
      }
      
      return count;
   }
}