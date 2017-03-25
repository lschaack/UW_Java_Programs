import java.util.*;
import java.io.*;

public class AssassinChecker {
   public static void main(String[] args) throws FileNotFoundException {
      List<String> names = new ArrayList<String>();
      Scanner nameRunner = new Scanner(new File("names.txt"));
      String tempName;
      
      while (nameRunner.hasNextLine()) {
         names.add(nameRunner.nextLine());
      }
      
      AssassinManager test = new AssassinManager(names);
      System.out.println(test);
      test.printKillRing();
   }
}