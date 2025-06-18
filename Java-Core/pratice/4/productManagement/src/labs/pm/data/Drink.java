/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public final class Drink extends Product {
    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    @Override
    public Product applyRating(Rating rating) {
        return new Drink(getId(), getName(),getPrice(),rating);
    }

    @Override
    public BigDecimal getDiscountRate(){
        LocalTime now = LocalTime.now();

        return now.isAfter(LocalTime.of(17, 30)) &&  now.isBefore(LocalTime.of(18, 30)) ? super.getDiscountRate() : BigDecimal.ZERO;
    }
}