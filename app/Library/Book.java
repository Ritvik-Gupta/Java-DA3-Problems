package app.Library;

public final class Book implements Comparable<Book> {
   private final String name;
   private final String author;
   private final int price;
   private final int numCopies;

   public Book(String name, String author, int price, int numCopies) {
      this.name = name;
      this.author = author;
      this.price = price;
      this.numCopies = numCopies;
   }

   public boolean hasName(String bookName) {
      return name.equals(bookName);
   }

   @Override
   public int compareTo(Book book) {
      return book.name.compareTo(name);
   }

   @Override
   public String toString() {
      return "\n" +
      "\n\tName :\t" + name + 
      "\n\tAuthor :\t" + author + 
      "\n\tPrice :\t" + price + 
      "\n\tNumber of Copies :\t" + numCopies;
   }

   public static int compare(Book bookA, Book bookB) {
      return bookA.compareTo(bookB);
   }
}
