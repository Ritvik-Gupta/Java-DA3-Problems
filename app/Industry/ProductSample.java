package app.Industry;

import java.io.Serializable;

public final class ProductSample implements Serializable {
   public final String productId;
   public final int diameter;
   public final int length;
   public final int weight;

   public ProductSample(String productId, int diameter, int length, int weight) {
      this.productId = productId;
      this.diameter = diameter;
      this.length = length;
      this.weight = weight;
   }

   public boolean hasAttributesForQA() {
      return length == 10 && diameter == 3 && weight == 100;
   }
}
