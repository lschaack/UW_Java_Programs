// Lucas Schaack
// 4/16/15
// CSE143
// Ta: Radu Cracut
// Section AS
// Helper methods are beneath all public methods
//
/* Stores HTML code as a collection of HTML tags, which can be added, removed, or fixed by
 * way of closing all unclosed tags, and deleting those which were never opened in the
 * first place. */
 
import java.util.List;
import java.util.ArrayList;
import cse143.util.*;

public class HTMLManager {
   
   private List<HTMLTag> tagList;
   
   /* Initializes a new HTMLManager from a queue of HTMLTag objects.
    * The queue can be empty, but a null queue will throw an IllegalArgumentException. */
   public HTMLManager(Queue<HTMLTag> page) {
      if (page == null) {
         throw new IllegalArgumentException("https://youtu.be/TOJG5mF6OLs");
      }
      
      this.tagList = new ArrayList<HTMLTag>();
      
      while (!page.isEmpty()) {
         this.tagList.add(page.dequeue());
      }
   }
   
   /* Adds a new HTMLTag at the specified index.
    * Invalid index throws IllegalArgumentException. */
   public void add(int index, HTMLTag tag) {
      this.indexException(index);
      
      this.tagList.add(index, tag);
   }
   
   /* Adds an HTMLTag from the specified index. 
    * Invalid index throws IllegalArgumentException. */
   public void remove(int index) {
      this.indexException(index);
      
      this.tagList.remove(index);
   }
   
   /* Returns a deep copy List of the tags contained in the HTML page. */
   public List<HTMLTag> getTags() {
      List<HTMLTag> tempTagList = new ArrayList<HTMLTag>();
      
      for (int i = 0; i < this.tagList.size(); i++) {
         // Makes use of the HTMLTag return of the getMatching method in HTMLTag.java
         // to construct new HTMLTag objects for tempTagList.
         tempTagList.add(this.tagList.get(i).getMatching().getMatching());
      }
      
      return tempTagList;
   }
   
   /* Returns a string representation of the HTML page. */
   public String toString() {
      String result = "";
      
      for (int i = 0; i < this.tagList.size(); i++) {
         result += this.tagList.get(i);
      }
      
      return result;
   }
   
   /* Closes all unclosed tags in the HTML page and deletes any closing tags that were never
    * opened. */
   public void fixHTML() {
      List<HTMLTag> result = new ArrayList<HTMLTag>();
      Stack<HTMLTag> toClose = new ArrayStack<HTMLTag>();
      
      for (int i = 0; i < this.tagList.size(); i++) {
         HTMLTag currTag = this.tagList.get(i);
         // If the current tag is an opening or self closing tag, add it to the result.
         // Then, if the current tag is opening, add it to the stack
         if (currTag.isOpening() || currTag.isSelfClosing()) {
            result.add(currTag);
            if (currTag.isOpening()) {
               toClose.push(currTag);
            }
         } else { // (currTag.isClosing())
            // If there is a matching tag at the top of the stack, the code is correct, so
            // the tag should be added to the result and its mate removed from the stack
            if (!toClose.isEmpty() && currTag.matches(toClose.peek())) {
               result.add(currTag);
               toClose.pop();
            // Otherwise, a mistake was made, so the variables are passed to a separate method
            // to do the work of fixing the mistake
            } else {
               this.fixClosingTag(result, toClose, currTag, i);
            }
         }
      }
      // empty out the stack to close all unclosed tags
      while (!toClose.isEmpty()) {
         result.add(toClose.pop().getMatching());
      }
      // change tagList reference to result List as a pseudo-return
      this.tagList = result;
   }
   
   /* Checks that the passed index is within the bounds of the HTML page. */
   private void indexException(int index) {
      if (index < 0 || index >= this.tagList.size()) {
         throw new IllegalArgumentException("http://nuclearsecrecy.com/nukemap/");
      }
   }
   
   /* Accepts all localized fixHTML variables and uses them to fix a mismatched closing tag.
    * Included separate from fixHTML in order to make fixHTML less bulky and more readable. */
   private void fixClosingTag(List<HTMLTag> result, Stack<HTMLTag> toClose,
                                                    HTMLTag currTag, int i) {
      boolean matchFound = false;
      // Combs through the stack until the tag's mate is found or the stack is emptied
      while (!toClose.isEmpty() && !matchFound) {
         result.add(toClose.pop().getMatching());
         if (!toClose.isEmpty() && currTag.matches(toClose.peek())) {
            result.add(currTag);
            toClose.pop();
            matchFound = true;
         }
      }
      /* If the stack has been consumed and no match has been found, the tag is a mistake,
       * so it is omitted from the result and all elements are returned to the stack to prevent
       * closing tags prematurely. */
      if (toClose.isEmpty() && !matchFound) {
         // return objects backwards from the end of result to maintain order
         for (int j = result.size() - 1; j > i; j--) {
            toClose.push(result.get(j));
         }
      }
   }
}