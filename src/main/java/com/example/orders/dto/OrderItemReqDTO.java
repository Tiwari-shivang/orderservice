package com.example.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemReqDTO {
    private String menuItemId, menuItemName, menuItemPrice;
    private int quantity;
    private Double totalPrice;
}
