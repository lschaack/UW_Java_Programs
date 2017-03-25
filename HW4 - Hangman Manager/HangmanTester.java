import java.util.*;
import java.io.*;

public class HangmanTester {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("tiny-dictionary.txt"));
      List<String> dictionary = new ArrayList<String>();
      
      while (input.hasNext()) {
         dictionary.add(input.next());
      }
      
      HangmanManager test = new HangmanManager(dictionary, 4, 3);
      System.out.println(test.words());
   }
}