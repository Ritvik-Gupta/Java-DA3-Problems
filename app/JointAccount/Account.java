package app.JointAccount;

public final class Account {
   //? Amount of Balance left in the Shared Joint Account
   private int balance;
   //? Base Limit of the Account
   public final int baseLimit;

   public Account(int balance, int baseLimit) throws BalanceException {
      if (baseLimit >= balance)
         throw new BalanceException("Base Limit cannot be higher than Balance");
      this.balance = balance;
      this.baseLimit = baseLimit;
   }

   public void printInfo() {
      System.out.println("Balance of Rs. " + balance + " and a Base Limit of Rs. " + baseLimit);
   }

   //* Can or cannot be Synchronized as it is a read only operation 
   synchronized int getBalance() {
      return balance;
   }

   //* Should be Synchronized as it a read and write operation 
   //* to the Balance according to the Base Limit and Shopping Amount
   synchronized void spend(int shoppingAmount) throws BalanceException {
      //? If the Purchase Amount deducted from the Balance then the Base Limit is crossed 
      if (shoppingAmount > balance - baseLimit)
         throw new BalanceException("Cannot complete the purchase. Base Limit reached");
      balance -= shoppingAmount;
   }
}
