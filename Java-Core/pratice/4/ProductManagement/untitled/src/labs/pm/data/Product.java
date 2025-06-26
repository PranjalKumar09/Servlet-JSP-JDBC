

package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* {@code Product} class represents properties and behaviours of
 * product object in Product Management System
* <br>
 * @author oracle
 * <br>
 * Each product can hav e a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 * @version 4.0
 */


public class Product {

    private final int id;
    private final String name;
    private final  BigDecimal price;
    private final Rating rating;


    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public Product(int id, String name, BigDecimal price) {
      this(id, name, price, Rating.NOT_RATED);
    }
    public Product() {
        this(0, "no name", BigDecimal.ZERO);
    }

    public Rating getRating(){
        return rating;
    }

    /**
     *  A constant that defines a
     *  {@link java.math.BigDecimal  BigDecimal} value of discount rate
     * value of the discountf
     *  <br>
    * */
    public static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.01");
    public int getId() {
        return id;
    }


    /**
     *  A constant that defines a
     *  {@link DISCOUNT_RATE BigDecimal} value of discount rate
     * @return a {@link java.math.BigDecimal BigDeciaml}
     * value of the discountf
     *  <br>
     * */

    public BigDecimal getPrice() {
        return price.multiply(DISCOUNT_RATE.setScale(2, RoundingMode.HALF_UP));
    }

    public Product applyRating(Rating rating) {
        return new Product(id, name, price, rating);
    }

}
