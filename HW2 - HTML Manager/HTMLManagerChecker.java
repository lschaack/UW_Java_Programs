import java.io.*;
import java.util.List;
import java.util.ArrayList;
import cse143.util.*;

public class HTMLManagerChecker {
   public static void main(String[] args) throws FileNotFoundException {
      HTMLParser parser = new HTMLParser(new File("veryBasic.html"));
      HTMLManager test = new HTMLManager(parser.parse());
      System.out.println(test);
   }
}