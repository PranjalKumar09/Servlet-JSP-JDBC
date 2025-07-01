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
import java.util.*;

public class ProductManager {
    private static Map<String, ResourceFormatter> formatters = Map.of(
            "en-GB", new ResourceFormatter(Locale.UK),
            "en-US", new ResourceFormatter(Locale.US),
            "rn-RU", new ResourceFormatter(new Locale("ru", "RU")),
            "fr-FR", new ResourceFormatter(Locale.FRANCE),
            "zh-CN", new ResourceFormatter(Locale.CHINA)
    );
//    public Product product;
//    public Review[] reviews = new Review[5];
    private Map<Product, List<Review>> products= new HashMap<>();
    private ResourceFormatter formatter;

    public static class ResourceFormatter{
        private Locale locale;
        private final ResourceBundle resources;
        private final NumberFormat moneyFormat;
        private final DateTimeFormatter dateFormat;

      private   ResourceFormatter(Locale locale){

        resources = ResourceBundle.getBundle("labs.pm.data.resources", locale);
        dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).localizedBy(locale);
        moneyFormat = NumberFormat.getCurrencyInstance(locale);
        }

        private String formatProduct(Product product){
          String type = (product instanceof Food) ? resources.getString("food") : resources.getString("drink");
          return MessageFormat.format(resources.getString("product"),
                  product.getName(), moneyFormat.format(product.getPrice()), product.getRating().getStars(), dateFormat.format( product.getBestBefore()), type );
        }
        private String formatReview(Review review){
          return MessageFormat.format(resources.getString("review"),
                  review.rating().getStars(), review.comments());
        }
        private String getText(String key){
          return resources.getString(key);
        }

    }
    public void changeLocale(String languageTag){
        formatter = formatters.getOrDefault(languageTag,formatters.get("en-GB"));
    }
    public ProductManager(String languageTag){
        changeLocale(languageTag);
    }

    public static Set<String> getSupportedLanguages(){
        return formatters.keySet();
    }

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        Product product = new Food(id, name, price, rating, bestBefore);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }
    public Product createProduct(int id, String name, BigDecimal price, Rating rating) {
        Product product = new Drink(id, name, price, rating);
        products.putIfAbsent(product, new ArrayList<>());
        return product;
    }
    public Product findProduct(int id) {
        Product result = null;
        for (Product product : products.keySet()) {
            if (product.getId() == id) {
                result = product;
                break;
            }
        }

        return result;
    }


    public Product reviewProduct(Product product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        if (reviews == null) reviews = new ArrayList<>();
        reviews.add(new Review(rating, comments));

        int sum = 0;
        for (Review review : reviews) {
            sum += review.rating().ordinal();
        }
        product = product.applyRating(Rateable.convert(Math.round((float) sum / reviews.size())));
        products.remove(product); // Remove old key (if hashCode/equals changed)
        products.put(product, reviews); // Put new key

        return product;
    }
    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);

        StringBuilder txt  = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append('\n');

        for (Review review : reviews) {
            if (review == null) break;
            txt.append(formatter.formatReview(review));

            txt.append('\n');
        }

         if (reviews.isEmpty())  txt.append(formatter.getText("no_reviews"));
        System.out.println(txt);
    }
    public void printProduct(Comparator<Product> sorter){
        List<Product> productList = new ArrayList<>(products.keySet());
        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
        for (Product product : productList) {
            txt.append(formatter.formatProduct(product));
            txt.append('\n');
        }
        System.out.println(txt);
    }
}
