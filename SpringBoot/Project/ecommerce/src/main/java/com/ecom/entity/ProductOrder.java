package com.ecom.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductOrder {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String orderId;
    private Date orderDate;
    @ManyToOne
    private Product product;
    private Double price;
    private Integer quantity;
    @ManyToOne
    private UserDtls userDtls;
    private String status;
    private String paymentType;
    @OneToOne(cascade = CascadeType.ALL)
    private OrderAddress orderAddress;

}
