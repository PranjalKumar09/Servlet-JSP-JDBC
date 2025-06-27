package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import static labs.pm.data.Rating.*;

/**
@author oracle
 **/


public class Shop {
    public static void main(String[] args) {
        ProductManager pm = new ProductManager();

        Product p1 =pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), NOT_RATED);
        pm.printProductReport();
        p1  = pm.reviewProduct(p1, FOUR_STAR, "Nice cup of tea");
        pm.printProductReport();
//        Product p2 =pm.createProduct(101, "DRINK", BigDecimal.valueOf(333), FOUR_STAR);
//        Product p3 = pm.createProduct(103, "FOOD",BigDecimal.valueOf(4.99) , THREE_STAR , LocalDate.now().plusDays(2));
//        Product p4 = pm.createProduct(103, "FOOD",BigDecimal.valueOf(4.99) , TWO_STAR , LocalDate.now().plusDays(2));
//        Product p5 = p3.applyRating(TWO_STAR);
//
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println(p3);
//        System.out.println(p4);
////        System.out.println(p3.getId() + " " + p3.getRating().getStars() + " " + p3.getPrice());
//
//        Product p6 = pm.createProduct(104, "Chocalte Drink", BigDecimal.valueOf(2,99), FIVE_STAR);
//        Product p7 = pm.createProduct(104, "Chocalte Drink", BigDecimal.valueOf(2,99), FIVE_STAR, LocalDate.now().plusDays(2));
//        Product p8 = p4.applyRating(FIVE_STAR);
//        Product p9 = p1.applyRating(FIVE_STAR);
//
//
//
//        System.out.println(p6.equals(p7));
//        System.out.println(p9);
//        System.out.println(p8);
//
//
//         System.out.println(p1.getBestBefore());

    }
}
