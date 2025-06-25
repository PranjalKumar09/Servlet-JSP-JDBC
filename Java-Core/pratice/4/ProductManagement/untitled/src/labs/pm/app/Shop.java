package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;

import static labs.pm.data.Rating.*;

/**
@author oracle
 **/


public class Shop {
    public static void main(String[] args) {
        Product product =new Product(101, "TEA", BigDecimal.valueOf(333));
        Product p2 =new Product(101, "TEA", BigDecimal.valueOf(333), FOUR_STAR);

        Product p3 = p2.applyRating(Rating.FOUR_STAR);
        System.out.println(p3.getId() + " " + p3.getRating().getStars() + " " + p3.getPrice());
    }
}
