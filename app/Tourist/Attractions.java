package app.Tourist;

import java.util.LinkedList;
import java.util.List;

public final class Attractions {
   private final LinkedList<TouristLocation> locations = new LinkedList<>();

   public void add(TouristLocation location) {
      int pos = 0;
      while (pos < locations.size()) {
         if (location.compareTo(locations.get(pos)) >= 0)
            break;
         ++pos;
      }
      locations.add(pos, location);
   }

   public int size() {
      return locations.size();
   }

   public List<TouristLocation> getCopy() {
      return List.copyOf(locations);
   }
}
