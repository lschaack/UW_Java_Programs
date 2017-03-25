// Lucas Schaack
// 4/9/15
// CSE143
// Ta: Radu Cracut
// Section AS
// Helper methods are beneath all public methods
//
/** Contains an editable inventory of the letters contained in a given string. LetterInventories
 *  can be added or subtracted from one another, and their index values can be manually set or
 *  used to calculate the percentage of a given letter. */

public class LetterInventory {

   private int[] letterInventory;
   private int size;
   public static final int NUM_LETTERS = 26;
   
   /** Initializes a new inventory with and empty string. */
   public LetterInventory() {
      this("");
   }
   
   /** Initializes a new inventory with counts of each letter of the given string. */
   public LetterInventory(String data) {
      this.size = 0;
      data = data.toLowerCase();
      this.letterInventory = new int[NUM_LETTERS];
      
      for (int i = 0; i < data.length(); i++) {
         if (isAlphabeticChar(data.charAt(i))) {
            this.letterInventory[toInt(data.charAt(i))]++;
            this.size++;
         }
      }
   }
   
   /** Returns the count of a given letter, throws IllegalArgumentException
    *  if given character is not a letter.                               */
   public int get(char letter) {
      letter = Character.toLowerCase(letter);
      if (!isAlphabeticChar(letter)) {
         throw new IllegalArgumentException();
      }
      
      return this.letterInventory[toInt(letter)];
   }
   
   /** Sets the given character to a given value, throws IllegalArgumentException
    *  if given character is not a letter.                                     */
   public void set(char letter, int value) {
      letter = Character.toLowerCase(letter);
      if (!isAlphabeticChar(letter)) {
         throw new IllegalArgumentException();
      }
      // Adds the difference of the new value and the old value (at the given letter) to size
      this.size += value - this.letterInventory[toInt(letter)];
      this.letterInventory[toInt(letter)] = value;
   }
   
   /** Returns a count of elements in the particular LetterInventory. */
   public int size() {
      return this.size;
   }
   
   /** Returns true if the particular LetterInventory is empty. */
   public boolean isEmpty() {
      return this.size == 0;
   }
   
   /** Returns a string representation of this LetterInventory. */
   public String toString(){
      String result = "";
      
      for (int i = 0; i < this.letterInventory.length; i++) {
         for (int j = 0; j < this.letterInventory[i]; j++) {
            result += toChar(i);
         }
      }
      return "[" + result + "]";
   }
   
   /** Returns a LetterInventory with the summed counts of each
    *  letter in both this and the provided LetterInventory.  */
   public LetterInventory add(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < NUM_LETTERS; i++) {
         // sets the value at each index to the sum of the two arrays at that index
         result.set(toChar(i), this.get(toChar(i)) + other.get(toChar(i)));
      }
      return result;
   }
   
   /** Returns a LetterInventory with the counts of the provided
    *  LetterInventory subtracted from this inventory.
    *  Returns null if any index value of the resultant array would be negative. */
   public LetterInventory subtract(LetterInventory other) {
      LetterInventory result = new LetterInventory();
      for (int i = 0; i < NUM_LETTERS; i++) {
         if (this.get(toChar(i)) - other.get(toChar(i)) < 0) {
            return null;
         }
         // sets the value at each index to the difference of the two arrays at that index
         result.set(toChar(i), this.get(toChar(i)) - other.get(toChar(i)));
      }
      return result;
   }
   
   /** Returns the percentage value (from 0 to 1) of a given char within this LetterInventory.
    *  throws IllegalArgumentException if given character is not a letter.                  */
   public double getLetterPercentage(char letter) {
      if (!isAlphabeticChar(letter)) {
         throw new IllegalArgumentException();
      }
      if (this.isEmpty()) {
         return 0.0;
      }
      
      return (double) this.get(letter) / this.size;
   }
   
   /** Returns true if given character is a lowercase letter */
   private boolean isAlphabeticChar(char letter) {
      return toInt(letter) >= 0 && toInt(letter) < NUM_LETTERS;
   }
   
   /** Returns the integer value of the given character calculated with respect to 'a'
    *  (the zero value). All lower case alphabetic characters will be between 0 and 25. */
   private int toInt(char letter) {
      return letter - 'a';
   }
   
   /** Returns the character value of a character given its distance from 'a' (an input would 1
     * outputs 'b', 2 outputs 'c', 25 outputs 'z', etc. */
   private char toChar(int distanceFromA) {
      return (char) ('a' + distanceFromA);
   }
}