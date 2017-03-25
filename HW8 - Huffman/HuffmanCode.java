// Lucas Schaack
// 6/5/15
// CSE143
// Ta: Radu Cracut
// Section AS
//
/* Represents a huffman code for a text file, capable of compressing and decompressing
 * a text file, as well as being saved to a formatted file or being constructed from
 * an existing saved state file. */
 
import java.util.Queue;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.PrintStream;

public class HuffmanCode {
   
   private HuffmanNode root;
   
   /* Initializes a new HuffmanCode object using an array of char frequencies in
    * which each index represents an ASCII value. */
   public HuffmanCode(int[] frequencies) {
      Queue<HuffmanNode> toCombine = new PriorityQueue<HuffmanNode>();
      // fill the queue
      for (int i = 0; i < frequencies.length; i++) {
         if (frequencies[i] > 0) {
            toCombine.add(new HuffmanNode(i, frequencies[i]));
         }
      }
      // reduce the queue to a single element
      while (toCombine.size() > 1) {
         HuffmanNode left = toCombine.remove();
         HuffmanNode right = toCombine.remove();
         // construct a new node linking the two and combining their frequencies
         HuffmanNode newNode = new HuffmanNode(left.freq + right.freq, left, right);
         toCombine.add(newNode);
      }
      
      this.root = toCombine.remove();
   }
   
   /* Initializes a new HuffmanCode object using a scanner linked to a .code file
    * containing pairs of ASCII values and their corresponding bit codes. */
   public HuffmanCode(Scanner input) {
      this.root = new HuffmanNode();
      int ascii;
      String code;
      
      while (input.hasNext()) {
         ascii = Integer.parseInt(input.nextLine());
         code = input.nextLine();
         this.root = this.addFromPath(this.root, ascii, code);
      }
   }
   
   /* Adds a leaf HuffmanNode to the HuffmanCode object according to the path
    * specified by the code, constructing the path to the leaf if necessary. */
   private HuffmanNode addFromPath(HuffmanNode current, int ascii, String code) {
      if (code.isEmpty()) { // the leaf needs to be constructed
         current = new HuffmanNode(ascii);
      } else { // (!code.isEmpty())
         // construct a new node if needed
         if (current == null) {
            current = new HuffmanNode();
         }   
         
         // choose whether to recurse left or right
         if (code.startsWith("0")) {
            // make sure that the substring index is correct
            current.left = addFromPath(current.left, ascii, code.substring(1));
         } else { // (code.startsWith("1")
            current.right = addFromPath(current.right, ascii, code.substring(1));
         }
      }
      
      return current;
   }
   
   /* Stores the current huffman codes in a formatted text file, containing pairs
    * of ASCII values and their corresponding bit codes, linked to the provided
    * print stream. */
   public void save(PrintStream output) {
      String code = "";
      this.save(output, this.root, code);
   }
   
   /* Stores the current huffman codes in a formatted text file linked to the
    * provided print stream. */
   private void save(PrintStream output, HuffmanNode current, String code) {
      if (current != null) {
         if (current.left == null && current.right == null) {
            output.println(current.character);
            output.println(code);
         } else {
            // choose left
            code = code + "0";
            this.save(output, current.left, code);
            // unchoose & choose right
            code = code.substring(0, code.length() - 1) + "1";
            this.save(output, current.right, code);
         }
      }
   }
   
   /* Translates bits from the provided input stream, linked to a previously compressed
    * file, to characters using the current huffman codes. Outputs the resultant text
    * to the provided print stream. */
   public void translate(BitInputStream input, PrintStream output) {
      while (input.hasNextBit()) {
         output.write(asciiFromBit(input, this.root));
      }
   }
   
   /* Translates bits from the provided input stream to characters using the current
    * huffman codes. */
   private int asciiFromBit(BitInputStream input, HuffmanNode current) {
      if (current.left == null && current.right == null) { // if (current is a leaf)
         return current.character;
      } else {
         if (input.nextBit() == 0) {
            return asciiFromBit(input, current.left);
         } else { // (input.nextBit() == 1)
            return asciiFromBit(input, current.right);
         }
      }
   }
}