public class LetterInventoryTest {
   public static void main(String[] args) {
      LetterInventory test = new LetterInventory("Hillary Rodham Clinton");
      System.out.println(test.size());
      System.out.println(test);
      System.out.println(test.getLetterPercentage('-'));
   }
}