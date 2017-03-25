import java.util.*;

public class Test {
   public static void main(String[] args) {
      mystery(612305);
   }
   
   public static void mystery(int n) {
      if (n <  0) {
         System.out.print("-");
         mystery(-n);
      }  else if (n < 10) {
         System.out.println(n);
      }  else {
         int   two = n % 100;
         System.out.print(two / 10);
         System.out.print(two % 10);
         mystery(n   / 100);
      }
   }
   
   public static Map<String, Integer> commonCustomers(Map<String, Integer> tvAccounts, Map<String, Integer> dataAccounts) {
      Map<String, Integer> common = new HashMap<String, Integer>();
      
      for (String name : tvAccounts) {
          if (dataAccounts.containsKey(name)) {
              common.put(name, tvAccounts.get(name));
          }
      }
      
      return common;
   }
}