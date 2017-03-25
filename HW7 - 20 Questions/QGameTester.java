import java.util.*;
import java.io.*;

public class QGameTester {
   public static void main(String[] args) throws FileNotFoundException {
      Scanner input = new Scanner(new File("spec-questions.txt"));
      QuestionsGame test = new QuestionsGame(input);
      PrintStream output = new PrintStream(System.out);
      
      test.printGame();
      System.out.println();
      test.saveQuestions(output);
   }
}