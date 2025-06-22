

package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
* {@code Product} class represents properties and behaviours of
 * product object in Product Management System
* <br>
 * @author oracle
 * <br>
 * Each product can have a discount, calculated based on a
 * {@link DISCOUNT_RATE discount rate}
 * @version 4.0
 */


public class Product {

    private int id;
    private String name;
    private BigDecimal price;
    private Rating rating;

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

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

}
