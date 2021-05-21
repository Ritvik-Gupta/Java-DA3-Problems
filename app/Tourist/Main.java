package app.Tourist;

import java.util.Scanner;

public final class Main {
   private static final Scanner S = new Scanner(System.in);

   private static TouristLocation promptForAttractionInfo() {
      System.out.println("Enter the following details about the Attraction :\n");

      System.out.print("\tName of the Attraction :\t");
      String name = S.nextLine();

      System.out.print("\tState ( Location ) :\t");
      String state = S.nextLine();

      System.out.print("\tAny Famous Spot :\t");
      String famousSpot = S.nextLine();

      return new TouristLocation(name, state, famousSpot);
   }

   public static void main(String[] args) {
      Attractions touristAttractions = new Attractions();

      System.out.println("Tourist Attractions :\n");
      System.out.println("\t(1) <ADD> :\tAdd a new Tourist Attraction Destination");
      System.out.println("\t(5) <DISPLAY> :\tDisplay all the Attractions collected");
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
               touristAttractions.add(promptForAttractionInfo());

            else if (command.equals("DISPLAY")) {
               System.out.println("\nNumber of Attractions :\t" + touristAttractions.size());
               touristAttractions.getCopy().forEach(System.out::println);
               System.out.println();
            }

            else
               throw new Exception("Invalid Command Specified");

         } catch (Exception err) {
            System.out.println("Error :\t" + err.getMessage());
         }
      }
   }
}
