import java.util.*;
import java.io.*;

public class GrammarTester {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner in = new Scanner(new File("math.txt"));
      List<String> sentenceList = new ArrayList<String>();
      
      while(in.hasNextLine()) {
         String line = in.nextLine();
         sentenceList.add(line);
      }
      
      Grammar test = new Grammar(sentenceList);
      
      System.out.println(test);
   }
}