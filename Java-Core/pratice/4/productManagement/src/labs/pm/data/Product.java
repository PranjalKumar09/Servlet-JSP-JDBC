

package labs.pm.data;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

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


public sealed abstract class Product implements Rateable<Product> permits Food, Drink {

    private final int id;

    public String getName() {
        return name;
    }

    private final String name;
    private final  BigDecimal price;
    private final Rating rating;
    public static final BigDecimal DISCOUNT_RATE = new BigDecimal("0.01");

    /**
     * Assumes that the best before is today
     * @return the current date
     */
    public LocalDate getBestBefore(){
        return LocalDate.now();
    }

    public BigDecimal getDiscountRate() {
        return DISCOUNT_RATE;
    }


    public Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    Product(int id, String name, BigDecimal price) {
      this(id, name, price, Rating.NOT_RATED);
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

    public abstract Product applyRating(Rating rating);

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating.getStars() +
                ", bestBefore=" + getBestBefore()+

                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null ) return false;
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && Objects.equals(price, product.price) && rating == product.rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
