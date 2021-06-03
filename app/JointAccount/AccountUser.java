package app.JointAccount;

public final class AccountUser extends Thread {
   //? Account UserName 
   private final String name;
   //? Reference to the actual Joint Account 
   private final Account account;
   //? Maximum Expenditure this User can ever spend
   private int maxExpenditure;

   public AccountUser(String name, Account account, int maxExpenditure) {
      this.name = name;
      this.account = account;
      this.maxExpenditure = maxExpenditure;
   }

   public void printInfo() {
      System.out.println("'" + name + "' has a max expendiure amount of Rs. " + maxExpenditure);
   }

   //* Expenditure Generation is Sudo-Random 
   //* and is in the range of 1 to {Max Expenditure}
   public int generateExpenditure() {
      return (int) Math.ceil(maxExpenditure * Math.random());
   }

   @Override
   public void run() {
      while (true) {
         //? Make the Thread go to sleep for some time so that 
         //? other Account Owners can occupy some space in the Main Execution Thread 
         try {
            sleep(500);
         } catch (InterruptedException err) {
            err.printStackTrace();
         }

         //* Synchronized According to the Account where all the read and write call are made.
         //* Needs to be Synchronized as to avoid Race Conditions but also because
         //* Every print call belongs to the same Transaction (in DBMS terms) and should be grouped.
         synchronized (account) {
            try {
               //? Generate a Random Expenditure Amount
               int shoppingAmount = generateExpenditure();

               //? Details before spending
               System.out.println("\nCurrent Balance in the Account :\t" + account.getBalance());
               System.out.println("'" + name + "' is about to spend Rs. " + shoppingAmount);

               //? Spend the Amount on Shopping
               account.spend(shoppingAmount);

               //? Details after spending
               System.out.println("Balance Left in the Account :\t" + account.getBalance() + "\n");
            } catch (BalanceException err) {
               System.err.println("\n" + err);

               //* Max Expenditure is Updated to the maximum of now what the User can spend 
               //* According to the deducted amount left in the Joint Account
               maxExpenditure = account.getBalance() - account.baseLimit;

               //? If User cannot spend anything anymore
               if (maxExpenditure == 0) {
                  System.err.println("'" + name + "' cannot spend anymore");
                  break;
               } else
                  System.err.println("'" + name + "' can spend a max of Rs. " + maxExpenditure + "\n");
            }
         }
      }
   }
}
