package com.example.orders.models;

import com.example.orders.dto.OrderReqDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrdersModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID user_id, restaurant_address_id, cart_id, delivery_id, payment_id;
    private String order_status, payment_status, special_instructions;
    private Double total_amount;
    private Timestamp created_at, updated_at;

    public OrdersModel(OrderReqDTO order){
        this.user_id = UUID.fromString(order.getUser_id());
        this.restaurant_address_id = UUID.fromString(order.getRestaurant_address_id());
        this.cart_id = UUID.fromString(order.getCart_id());
        this.order_status = order.getOrder_status();
        this.payment_status = order.getPayment_status();
        this.special_instructions = order.getSpecial_instructions();
        this.total_amount = order.getTotal_amount();
        this.created_at = Timestamp.from(Instant.now());
        this.updated_at = Timestamp.from(Instant.now());
    }
}
