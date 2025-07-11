package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Locale;

import static labs.pm.data.Rating.*;

/**
@author oracle
 **/


public class    Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager("en-GB" );

        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
//        p1 = pm.reviewProduct(p1, FIVE_STAR, "Absolutely loved it!");
        pm.parseProduct("F,103,Tea,1.99,0,  2012-01-02");
        pm.parseReview("101,4,Nice hot cup of tea");

//        p1 = pm.reviewProduct(p1, THREE_STAR, "It was okay, not great.");
//        p1 = pm.reviewProduct(p1, FOUR_STAR, "Pretty good, would buy again.");
//        p1 = pm.reviewProduct(p1, TWO_STAR, "Too strong for my taste.");
//        p1 = pm.reviewProduct(p1, ONE_STAR, "Didn't like it at all.");
//        p1 = pm.reviewProduct(p1, FOUR_STAR, "Nice aroma and flavor.");
//        p1 = pm.reviewProduct(p1, THREE_STAR, "Average, nothing special.");
//        p1 = pm.reviewProduct(p1, FIVE_STAR, "Perfect with biscuits!");
//        p1 = pm.reviewProduct(p1, TWO_STAR, "Not my cup of tea.");
//        p1 = pm.reviewProduct(p1, FOUR_STAR, "Good value for money.");

        pm.printProductReport(101);
//
//Product p2 = pm.createProduct(102, "Coffee", BigDecimal.valueOf(2.49), NOT_RATED);
//        p2 = pm.reviewProduct(p2, FIVE_STAR, "Excellent coffee!");
//        p2 = pm.reviewProduct(p2, FOUR_STAR, "Very good");
//        p2 = pm.reviewProduct(p2, THREE_STAR, "Average taste");
//        p2 = pm.reviewProduct(p2, TWO_STAR, "Not strong enough");
//        p2 = pm.reviewProduct(p2, ONE_STAR, "Too bitter");
//
//        // Product 3: Sandwich
//        Product p3 = pm.createProduct(103, "Sandwich", BigDecimal.valueOf(3.99), THREE_STAR);
//        p3 = pm.reviewProduct(p3, THREE_STAR, "Okay sandwich");
//        p3 = pm.reviewProduct(p3, FOUR_STAR, "Tasty and fresh");
//        p3 = pm.reviewProduct(p3, TWO_STAR, "Bread was stale");
//        p3 = pm.reviewProduct(p3, FIVE_STAR, "Best sandwich ever!");
//
//        // Product 4: Juice
//        Product p4 = pm.createProduct(104, "Juice", BigDecimal.valueOf(1.49), TWO_STAR);
//        p4 = pm.reviewProduct(p4, TWO_STAR, "Too sweet");
//        p4 = pm.reviewProduct(p4, THREE_STAR, "Good flavor");
//        p4 = pm.reviewProduct(p4, FOUR_STAR, "Refreshing");
//
//
//        Comparator<Product> ratingSorter = (pa, pb)-> pb.getRating().ordinal() -pa.getRating().ordinal();
//        Comparator<Product> priceSorter = (pa, pb)-> pb.getPrice().compareTo(pa.getPrice());
//
//
//        pm.printProducts( p -> p.getPrice().floatValue() < 2 ,(ratingSorter.thenComparing(priceSorter) ));
//
//        pm.getDiscount().forEach((stars, discount) -> System.out.println(stars + " " + discount));
//
//
    }
}
