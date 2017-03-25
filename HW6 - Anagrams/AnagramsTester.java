import java.util.*;
import java.io.*;

public class AnagramsTester {
   public static void main(String[] args) throws FileNotFoundException {
      // open the dictionary file
      Scanner console = new Scanner(System.in);
      System.out.print("What is the name of the dictionary file? ");
      Scanner input = new Scanner(new File(console.nextLine()));
      // Read dictionary into a List
      List<String> dictionary = new ArrayList<String>();
      while (input.hasNextLine()) {
         dictionary.add(input.nextLine());
      }
      
      Anagrams test = new Anagrams(dictionary);
      System.out.println(test);
   }
}