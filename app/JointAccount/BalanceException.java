package app.JointAccount;

public class BalanceException extends Exception {
   public BalanceException(String message) {
      super(message);
   }

   @Override
   public String toString() {
      return "Balance Error : " + getMessage();
   } 
}
