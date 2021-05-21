package app.Library;

public final class Book implements Comparable<Book> {
   public final String name;
   public final String author;
   public final int price;
   public final int numCopies;

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
      "\tAuthor :\t" + author + 
      "\tPrice :\t" + price + 
      "\tNumber of Copies :\t" + numCopies;
   }

   public static int compare(Book bookA, Book bookB) {
      return bookA.compareTo(bookB);
   }
}