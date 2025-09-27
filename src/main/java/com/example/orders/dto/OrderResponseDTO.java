package com.example.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String id, user_id, restaurant_address_id, cart_id, delivery_id, payment_id;
    private String order_status, payment_status, special_instructions;
    private Double total_amount;
    private List<OrderItemDTO> items;
    private Timestamp created_at, updated_at;
}
