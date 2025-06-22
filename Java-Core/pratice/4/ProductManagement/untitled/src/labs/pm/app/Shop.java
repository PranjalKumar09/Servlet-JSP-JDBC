package labs.pm.app;

import labs.pm.data.*;

import java.math.BigDecimal;

/**
@author oracle
 **/


public class Shop {
    public static void main(String[] args) {
        Product product =new Product();
        product.setId(0xf__2_3);
        product.setName("Tea");
//        product.setPrice(BigDecimal.valueOf(1.99));

        System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice());
    }
}
