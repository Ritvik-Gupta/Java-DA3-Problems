package app.Industry;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
   private static final Scanner S = new Scanner(System.in);
   private static final String fileName = "./app/Industry/stream.txt";

   private static ProductSample promptProduct() {
      System.out.println("\nEnter the details of Product Sample");

      System.out.print("\tProduct ID :\t");
      String productId = S.next();

      System.out.print("\tLength :\t");
      int length = S.nextInt();

      System.out.print("\tDiameter :\t");
      int diameter = S.nextInt();

      System.out.print("\tWeight :\t");
      int weight = S.nextInt();

      S.nextLine();
      return new ProductSample(productId, diameter, length, weight);
   }

   public static void main(String[] args) {
      HashSet<String> incorrectSampleSet = new HashSet<>();
      ArrayList<String> correctSampleList = new ArrayList<>();

      System.out.print("\nEnter the number of Product Samples to be taken :\t");
      int numSamples = S.nextInt();

      for (int pos = 0; pos < numSamples; ++pos) {
         ProductSample sample = promptProduct();

         try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));

            outputStream.writeObject(sample);
            outputStream.close();

            ProductSample deserializedSample;
            try {
               ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));

               deserializedSample = (ProductSample) inputStream.readObject();
               inputStream.close();

               if (deserializedSample.hasAttributesForQA())
                  correctSampleList.add(deserializedSample.productId);
               else
                  incorrectSampleSet.add(deserializedSample.productId);
            } catch (ClassNotFoundException err) {
               System.out.println("Class Not Found Error : " + err.getMessage());
            }
         } catch (IOException err) {
            System.out.println("IO Error :\t" + err.getMessage());
         }
      }

      System.out.println("\nThe Product IDs of Defective samples are :");
      for (String sample : incorrectSampleSet)
         System.out.println("\t" + sample);

      correctSampleList.sort((a, b) -> a.compareTo(b));
      System.out.println("\nThe Product IDs of Correct samples in Sorted Order are :");
      for (String sample : correctSampleList)
         System.out.println("\t" + sample);
   }
}
