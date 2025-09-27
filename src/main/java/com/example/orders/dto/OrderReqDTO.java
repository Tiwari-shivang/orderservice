package com.example.orders.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderReqDTO {
    private String user_id, restaurant_address_id, cart_id, order_status, payment_status, special_instructions;
    private Double total_amount;
    private Timestamp created_at, updated_at;
    private List<OrderItemReqDTO> items;
}
