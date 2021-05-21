package app.Library;

import java.util.HashMap;
import java.util.List;

import java.util.Map.Entry;

import org.w3c.dom.ranges.RangeException;

public final class Library {
   private final HashMap<Integer, List<Book>> racks = new HashMap<>();

   public void addBookToRack(Book book, int rackNo) {
      if (racks.containsKey(rackNo)) {
         if (racks.get(rackNo).size() < 5)
            racks.get(rackNo).add(book);
         else
            throw new RangeException((short) 5, "Rack cannot contain more than 5 books");
      } else
         racks.put(rackNo, List.of(book));
   }

   public int search(String bookName) {
      for (Entry<Integer, List<Book>> entry : racks.entrySet())
         if (entry.getValue().stream().anyMatch(book -> book.hasName(bookName)))
            return entry.getKey();
      throw new IndexOutOfBoundsException("No such Book present in the Library with the given name");
   }

   public List<Book> sort(int rackNo) {
      if (racks.containsKey(rackNo)) {
         List<Book> sortedRack = List.copyOf(racks.get(rackNo));
         sortedRack.sort(Book::compare);
         return sortedRack;
      }
      throw new IndexOutOfBoundsException("Library has no Rack with number specified");
   }
}
