package app.JointAccount;

public final class Main {
   public static void main(String[] args) throws BalanceException, InterruptedException {
      Account account = new Account(25000, 5000);
      AccountUser husbandAccn = new AccountUser("Husband", account, 5000);
      AccountUser wifeAccn = new AccountUser("Wife", account, 10000);

      System.out.println();

      account.printInfo();
      husbandAccn.printInfo();
      wifeAccn.printInfo();

      System.out.println();

      husbandAccn.start();
      wifeAccn.start();

      husbandAccn.join();
      wifeAccn.join();
   }
}
