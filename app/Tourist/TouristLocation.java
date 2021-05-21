package app.Tourist;

public final class TouristLocation implements Comparable<TouristLocation> {
   public final String name;
   public final String state;
   public final String famousSpot;

   public TouristLocation(String name, String state, String famousSpot) {
      this.name = name;
      this.state = state;
      this.famousSpot = famousSpot;
   }

   @Override 
   public int compareTo(TouristLocation location) {
      return state.compareTo(location.state);
   }

   @Override
   public String toString() {
      return "\n\n\tName :\t" + name + "\n\tState :\t" + state + "\n\tFamous Spot :\t" + famousSpot;
   }
}
