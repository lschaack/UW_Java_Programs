// Lucas Schaack
// 4/23/15
// CSE143
// Ta: Radu Cracut
// Section AS
// Helper methods are beneath all public methods
//
/** Manages a game of Assassin with the ability to create a printable list of players and their
  * targets, as well as a separate list of "dead" players. Each time a player is "killed," they
  * are automatically removed from the running game and placed into the dead player list. */

import java.util.List;
import java.util.ArrayList;

public class AssassinManager {

   private AssassinNode killRingFront;
   private AssassinNode graveyardFront;
   
   /** Initializes a new manager using a list of player names. 
     * Throws IllegalArgumentException if passed an empty list. */
   public AssassinManager(List<String> names) {
      if (names.isEmpty() || names == null) {
         throw new IllegalArgumentException("https://youtu.be/qeT1k0rEGps");
      }
      
      this.killRingFront = new AssassinNode(names.get(0));
      AssassinNode current = this.killRingFront;
      
      for (int i = 1; i < names.size(); i++) {
         current.next = new AssassinNode(names.get(i));
         current = current.next;
      }
   }

   /** Prints the contents of the list of active players. */
   public void printKillRing() {
      if (this.isGameOver()) {
         System.out.println("    X stalking X");
      } else {
         AssassinNode current = this.killRingFront;
         while (current.next != null) {
            System.out.println("    " + current.name + " is stalking " + current.next.name);
            current = current.next;
         }
         System.out.println("    " + current.name + " is stalking " + this.killRingFront.name);
      }
   }
   
   /** Prints the contents of the list of dead players. */
   public void printGraveyard() {
      if (this.graveyardFront != null) {  
         AssassinNode current = this.graveyardFront;
         while (current != null) {
            System.out.println("    " + current.name + " was killed by " + current.killer);
            current = current.next;
         }
      }
   }
   
   /** Returns true if a given name is found in the list of active players. */
   public boolean killRingContains(String name) {
      return this.checkForString(this.killRingFront, name);
   }
   
   /** Returns true if a given name is found in the list of dead players. */
   public boolean graveyardContains(String name) {
      return this.checkForString(this.graveyardFront, name);
   }
   
   /** Returns true if there is only one remaining player in the game. */
   public boolean isGameOver() {
      return this.killRingFront.next == null;
   }
   
   /** Returns the name of the winner if the game is over. Otherwise returns null. */
   public String winner() {
      if (this.isGameOver()) {
         return this.killRingFront.name;
      } else {
         return null;
      }
   }
   
   /** "Kills" a player, removing them from the kill ring and into the front of the graveyard.
     * Throws IllegalStateException if called at game over, 
     * Throws IllegalArgumentException if called on a name that is not in the kill ring. */
   public void kill(String name) {
      if (this.isGameOver()) {
         throw new IllegalStateException("http://gfycat.com/MildJollyAsiandamselfly");
      } else if (!this.killRingContains(name)) {
         throw new IllegalArgumentException("http://iacopoapps.appspot.com/hopalongwebgl/");
      }
      // used to store the node that is to be added to the graveyard
      AssassinNode temp;
      // sets the killer field of the killed node, removes the killed node from the kill ring
      if (this.killRingFront.name.equalsIgnoreCase(name)) {
         // sets the name of the killer of killRingFront to the last name in killRing
         this.killRingFront.killer = this.goToEnd(this.killRingFront).name;
         temp = this.killRingFront;
         this.killRingFront = this.killRingFront.next;
      } else {
         AssassinNode current = this.killRingFront;
         // assigns current to the node before that of the killed node
         while (!current.next.name.equalsIgnoreCase(name)) {
            current = current.next;
         }
         current.next.killer = current.name;
         temp = current.next;
         current.next = current.next.next;
      }
      // adds temp to graveyard
      temp.next = this.graveyardFront;
      this.graveyardFront = temp;
   }
   
   /** Returns true if a name is found within a list, given the name and the front of the list. */
   private boolean checkForString(AssassinNode front, String name) {
      AssassinNode current = front;
      while (current != null) {
         if (current.name.equalsIgnoreCase(name)) {
            return true;
         }
         current = current.next;
      }
      return false;
   }
   
   /** Returns the ending node of a list, given the front of the list.
     * Assumes that the node passed is not null. */
   private AssassinNode goToEnd(AssassinNode front) {
      AssassinNode current = front;
      while (current.next != null) {
         current = current.next;
      }
      return current;
   }
}