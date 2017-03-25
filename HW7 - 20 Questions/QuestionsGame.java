// Lucas Schaack
// 5/31/15
// CSE143
// Ta: Radu Cracut
// Section AS
//
/* Uses a formatted question/answer file to create an object capable of playing
 * a round of twenty questions with the user, in which the user guesses and the
 * attempts to answer, adding another question/answer pair to the file if it
 * fails. */

import java.util.Scanner;
import java.io.PrintStream;

public class QuestionsGame {
   
   private QuestionNode root;
   private Scanner console;
   
   /* Initializes a new QuestionsGame object using a single String object. */
   public QuestionsGame(String object) {
      this(new Scanner(object));
   }
   
   /* Initializes a new QuestionsGame object using a Scanner initialized to an
    * input file or String object. */
   public QuestionsGame(Scanner input) {
      this.console = new Scanner(System.in);
      this.root = this.construct(input, this.root);
   }
   
   /* Returns the QuestionNode root of a binary tree formed by a scanner initialized
    * to a formatted input file or String object. */
   private QuestionNode construct(Scanner input, QuestionNode current) {
      if (input.hasNextLine()) {
         String type = input.nextLine().toLowerCase();
         Boolean isQuestion = type.contains("q");
         
         current = new QuestionNode(input.nextLine());
         if (isQuestion) {
            current.left = this.construct(input, current.left);
            current.right = this.construct(input, current.right);
         }
      }
      
      return current;
   }
   
   /* Exports a text representation of this QuestionsGame object to a PrintStream.
    * Throws IllegalArgumentException if the PrintStream is null. */
   public void saveQuestions(PrintStream output) {
      if (output == null) {
         throw new IllegalArgumentException("");
      }
      
      saveQuestions(output, this.root);
   }
   
   /* Exports a text representation of this QuestionsGame object to a PrintStream. */
   private void saveQuestions(PrintStream output, QuestionNode current) {
      if (current != null) {
         if (current.left != null || current.right != null) {
            output.println("Q:");
         } else {
            output.println("A:");
         }
         output.println(current.data);
         
         saveQuestions(output, current.left);
         saveQuestions(output, current.right);
      }
   }
   
   /* Plays a single game of twenty questions with the user. */
   public void play() {
      this.play(this.root, null);
   }
   
   /* Plays a single game of twenty questions with the user, appends a new
    * question/answer pair to the QuestionsGame object if the wrong object is
    * guessed. */
   private void play(QuestionNode current, QuestionNode previous) {
      // probably going to need a current == null case
      if (current.left == null && current.right == null) {
         System.out.println("I guess that your object is " + current.data + "!");
         System.out.print("Am I right? (y/n)? ");
         if (this.console.nextLine().trim().toLowerCase().startsWith("y")) {
            System.out.println("Awesome! I win!");
         } else {
            System.out.println("Boo! I Lose. Please help me get better!");
            this.appendAnswer(current, previous);
         }
      } else {
         // keeps track of previous node for appending new question/answer combos
         previous = current;
         System.out.print(current.data + " (y/n)? ");
         if (this.console.nextLine().trim().toLowerCase().startsWith("y")) {
            this.play(current.left, previous);
         } else {
            this.play(current.right, previous);
         }
      }
   }
   
   /* Prompts the user for a new object and a question to distinguish between that
    * object and another, and uses this information to append a new node to the
    * QuestionsGame object which differentiates the two. */
   private void appendAnswer(QuestionNode current, QuestionNode previous) {
      System.out.print("What is your object? ");
      String newAnswer = this.console.nextLine();
      String oldAnswer = current.data;
      System.out.println("Please give me a yes/no question that distinguishes between " + 
            newAnswer + " and " + oldAnswer);
      System.out.print("Q: ");
      String question = this.console.nextLine();
      // add the question in the place of the answer being replaced
      if (previous.left.data.equals(oldAnswer)) {
         previous.left = new QuestionNode(question);
         current = previous.left;
      } else {
         previous.right = new QuestionNode(question);
         current = previous.right;
      }
      // add the answers in the correct places
      System.out.println("Is the answer \"yes\" for " + newAnswer + "? (y/n)? ");
      
      if (this.console.nextLine().trim().toLowerCase().startsWith("y")) {
         current.left = new QuestionNode(newAnswer);
         current.right = new QuestionNode(oldAnswer);
      } else {
         current.left = new QuestionNode(oldAnswer);
         current.right = new QuestionNode(newAnswer);
      }
   }
}