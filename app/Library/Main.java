package app.Library;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.w3c.dom.ranges.RangeException;

public final class Main {
   private static final Scanner S = new Scanner(System.in);
   private static final Library library = new Library();

   private static final Pattern searchRegex = Pattern.compile("^SEARCH\\s\\w+$");
   private static final Pattern sortRegex = Pattern.compile("^SORT\\s\\d+$");

   private static void promptForBookInfo() {
      System.out.print("Enter the Rack where the Book is to be placed :\t");
      int rackNo = S.nextInt();

      S.nextLine();
      if (rackNo < 0)
         throw new RangeException((short) -1, "Rack Number should be positive");

      System.out.println("Enter the following details about the Book :\n");

      System.out.print("\tName :\t");
      String name = S.nextLine();

      System.out.print("\tAuthor :\t");
      String author = S.nextLine();

      System.out.print("\tPrice :\t");
      int price = S.nextInt();

      System.out.print("\tNumber of Copies Available :\t");
      int numCopies = S.nextInt();

      S.nextLine();
      library.addBookToRack(new Book(name, author, price, numCopies), rackNo);
   }

   public static void main(String[] args) {
      System.out.println("Library Management System :\n");
      System.out.println("\t(1) <ADD> :\tAdd a new Book in a particular Rack");
      System.out.println("\t(5) <SEARCH #S> :\tLook for the given Book and display the Rack it is in");
      System.out.println("\t(5) <SORT #N> :\tLook for the given Rack and display the Books in it");
      System.out.println("\t(6) <END> :\tEnd the Simulation");

      while (true) {
         System.out.print("\nEnter a Command ::\t");
         String command = S.nextLine();

         try {
            if (command.equals("END")) {
               System.out.println("Terminating ...\n\n");
               break;
            }

            else if (command.equals("ADD"))
               promptForBookInfo();

            else if (searchRegex.matcher(command).matches()) {
               String bookName = command.substring(7);
               System.out.println("\nBook has been found in the Rack [ " + library.search(bookName) + " ]");
            }

            else if (sortRegex.matcher(command).matches()) {
               int rackNo = Integer.parseInt(command.substring(5));
               List<Book> sortedRack = library.sort(rackNo);

               System.out.println("\nNumber of Books in the Rack :\t" + sortedRack.size());
               sortedRack.forEach(System.out::println);
               System.out.println();
            }

            else
               throw new Exception("Invalid Command Specified");

         } catch (IndexOutOfBoundsException err) {
            System.out.println("Index Out of Bounds Error :\t" + err.getMessage());
         } catch (RangeException err) {
            System.out.println("Range Error :\t" + err.getMessage());
         } catch (Exception err) {
            System.out.println("Error :\t" + err.getMessage());
            err.printStackTrace();
         }
      }
   }
}
