package app;

import java.util.List;
import java.util.PriorityQueue;

public final class Rainfall {
   public static void main(String[] args) {
      PriorityQueue<Double> rainfallEstimates = new PriorityQueue<>(
         List.of(10.2, 11.9, 8.0, 11.2, 10.8, 6.9, 8.2, 11.5, 10.4, 8.7, 7.8, 7.5)
      );

      double averageRainfall = 
         rainfallEstimates.stream().reduce(0d, Double::sum) / rainfallEstimates.size();

      System.out.println("Average Rainfall :\t" + averageRainfall);

      int numAboveAvgRainfall = rainfallEstimates
         .stream()
         .reduce(0, (count, amount) -> count + (amount > averageRainfall ? 1 : 0), Integer::sum);

      System.out.println("Number of Above Average Rainfall Estimates :\t" + numAboveAvgRainfall);
   }
}
