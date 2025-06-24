/*
 * Copyright (c) 2025. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package labs.pm.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public class ProductManager {
    private final ResourceBundle resources;
    private final NumberFormat moneyFormat;
    private final DateTimeFormatter dateFormat;
    public Product product;
    public Review[] reviews = new Review[5];

    public ProductManager(Locale locale) {
        resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);

    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        return product = new Food(id, name, price, rating, bestBefore);
    }
    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        return product = new Drink(id, name, price, rating);
    }

    public Product reviewProduct(Product product, Rating rating, String comments) {
//        review = new Review(rating, comments);

        if (reviews[reviews.length - 1] != null) {
            reviews = Arrays.copyOf(reviews, reviews.length + 1);
        }
        int sum = 0, i = 0;
        boolean reviewed = false;
        while(i < reviews.length && !reviewed) {
            if (reviews[i] == null) {
                reviews[i] = new Review(rating, comments);
                reviewed = true;
            }
            sum+= reviews[i].rating().ordinal();
            i++;
        }
        this.product = product.applyRating(Rateable.convert(Math.round((float)sum/i)));


        this.product =  product.applyRating(rating);
        return this.product;
    }
    public void printProductReport(){
        StringBuilder txt  = new StringBuilder();
        String type = (product instanceof Drink) ?   resources.getString("drink"):  resources.getString("food") ;
        txt.append(MessageFormat.format(resources.getString("product"),
                product.getName(), moneyFormat.format(product.getPrice()), product.getRating().getStars(), dateFormat.format( product.getBestBefore()), type ));


        txt.append('\n');

        for (Review review : reviews) {
            if (review == null) break;
            txt.append(MessageFormat.format(resources.getString("review"),
                        review.rating().getStars(), review));

            txt.append('\n');
        }
         if (reviews[0]==null)txt.append(resources.getString("no_reviews"));
        System.out.println(txt);
    }
}
