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
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProductManager {
    private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

    private ResourceBundle config = ResourceBundle.getBundle("labs.pm.data.config");
    private MessageFormat reviewFormat = new MessageFormat(config.getString("review.data.format"));
    private MessageFormat productFormat = new MessageFormat(config.getString("product.data.format"));


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
    public Product findProduct(int id) throws ProductManagerException {
      return  products.keySet().stream().filter(product -> product.getId() == id).findFirst().orElseThrow(() -> new ProductManagerException("Product with id "+id +" not found"));
    }


    public Product reviewProduct(Product product, Rating rating, String comments) {
        List<Review> reviews = products.get(product);
        if (reviews == null) reviews = new ArrayList<>();
        reviews.add(new Review(rating, comments));
//
//        int sum = 0;
//        for (Review review : reviews) {
//            sum += review.rating().ordinal();
//        }
        product = product.applyRating((int)Math.round(reviews.stream().mapToInt(i -> i.rating().ordinal()).average().orElse(0)));
        products.remove(product); // Remove old key (if hashCode/equals changed)
        products.put(product, reviews); // Put new key

        return product;
    }
    public Product reviewProduct(int id, Rating rating, String comments) {
        try {
            return reviewProduct(findProduct(id), rating, comments);
        } catch (ProductManagerException e) {
            logger.log(Level.INFO, e.getMessage());
            return null;
        }
    }



    public void printProductReport(int id) {
        Product product = null;
        try {
            product = findProduct(id);
            printProductReport(product);
        } catch (ProductManagerException e) {
            logger.log(Level.INFO, e.getMessage());
        }
    }
    public void printProductReport(Product product) {
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);

        StringBuilder txt  = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append('\n');

        if (reviews.isEmpty())  txt.append(formatter.getText("no_reviews"+'\n'));
        else {
            txt.append(reviews.stream().map(r-> formatter.formatReview(r)+'\n').collect(Collectors.joining()));
//            reviews.stream().forEach(r -> txt.append( formatter.formatReview(r)+'\n'));

        }
//        for (Review review : reviews) {
//            if (review == null) break;
//            txt.append(formatter.formatReview(review));
//
//            txt.append('\n');
//        }

//         if (reviews.isEmpty())  txt.append(formatter.getText("no_reviews"));
         if (reviews.isEmpty())  txt.append(formatter.getText("no_reviews"+'\n'));
        System.out.println(txt);
    }
    public void printProduct(Comparator<Product> sorter){
//        List<Product> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
//        for (Product product : productList) {
//            txt.append(formatter.formatProduct(product));
//            txt.append('\n');
//        }
        products.keySet().stream().sorted(sorter).forEach(i -> txt.append(formatter.formatProduct(i)));

        System.out.println(txt);
    }

    public void parseProduct(String text){
        try {
            Object[] values=  productFormat.parse(text);
            int id = Integer.parseInt((String)values[1]);
            String name = (String) values[2];
            BigDecimal price = (BigDecimal) values[3];
            Rating rating = Rateable.convert(Integer.parseInt((String)values[4]));
            switch ((String)values[0]){
                case "D":
                    createProduct(id,name,price,rating);
                break;
                case "F":
                    LocalDate bestBefore = LocalDate.parse((String)values[5]);
                    createProduct(id,name,price,rating,bestBefore);
            }

//            reviewProduct(id, Rateable.convert(Integer.parseInt((String)values[1])),(String)values[2]);
        } catch (ParseException  | NumberFormatException  | DateTimeParseException e ) {
            logger.log(Level.WARNING, "Error parsing review "+text + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public void parseReview(String text){
        try {
            Object[] values=  reviewFormat.parse(text);
            reviewProduct(Integer.parseInt((String)values[0]), Rateable.convert(Integer.parseInt((String)values[1])),(String)values[2]);
        } catch (ParseException e) {
            logger.log(Level.WARNING, "Error parsing review "+text + e.getMessage());
            throw new RuntimeException(e);
        }
    }



    public void printProducts(Predicate<Product> filter , Comparator<Product> sorter)
    {
//        List<Product> productList = new ArrayList<>(products.keySet());
//        productList.sort(sorter);
        StringBuilder txt = new StringBuilder();
//        for (Product product : productList) {
//            txt.append(formatter.formatProduct(product));
//            txt.append('\n');
//        }
        products.keySet().stream().sorted(sorter).forEach(i -> txt.append(formatter.formatProduct(i)));

        System.out.println(txt);
    }
    public Map<String, String> getDiscount(){
        return products.keySet().stream().collect(
                Collectors.groupingBy(product ->  product.getRating().getStars(),
                        Collectors.collectingAndThen(
                                Collectors.summingDouble(
                                        product -> product.getDiscountRate().doubleValue()), discount -> formatter.moneyFormat.format(discount))));

    }
}
