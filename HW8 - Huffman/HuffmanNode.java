// Lucas Schaack
// 6/5/15
// CSE143
// Ta: Radu Cracut
// Section AS
//
/* Represents a single node of a HuffmanCode object, comparable to another
 * HuffmanNode object. */

public class HuffmanNode implements Comparable<HuffmanNode> {

   public final int character; // stores ascii value of intended char, default value of 0
   public final int freq; // default value of -1
   public HuffmanNode left;
   public HuffmanNode right;
   
   /* Constructs a HuffmanNode object from the ascii value of the intended character,
    * the frequency of that character, and the following left and right nodes. */
   public HuffmanNode(int character, int frequency, HuffmanNode left, HuffmanNode right) {
      this.character = character;
      this.freq = frequency;
      this.left = left;
      this.right = right;
   }
   
   /* Constructs a HuffmanNode object from a character, the frequency of that
    * character, and the left and right nodes. */
   public HuffmanNode(char character, int frequency, HuffmanNode left, HuffmanNode right) {
      this((int)character, frequency, left, right);
   }
   
   /* Constructs a HuffmanNode object from the ascii value of the intended character
    * and the frequency of that character. */
   public HuffmanNode(int character, int frequency) {
      this(character, frequency, null, null);
   }
   
   /* Constructs a HuffmanNode object from the ascii value of the intended character. */
   public HuffmanNode(int character) {
      this(character, -1, null, null);
   }
   
   /* Constructs a HuffmanNode object from a frequency and the following left and
    * right nodes. */
   public HuffmanNode(int frequency, HuffmanNode left, HuffmanNode right) {
      this(0, frequency, left, right);
   }
   
   /* Constructs a HuffmanNode object with default character and frequency. */
   public HuffmanNode() {
      this(0, -1, null, null);
   }
   
   /* Compares two HuffmanNode objects, returning positive if the frequency of this
    * node is greater than that of the other, negative if it is less, and zero if
    * it is equal. */
   public int compareTo(HuffmanNode other) {
      return ((Integer)this.freq).compareTo(other.freq);
   }
}