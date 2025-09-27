package com.example.orders.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItemModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    @Column(name = "menu_item_id")
    private String menuItemId;
    @Column(name = "menu_item_name")
    private String menuItemName;
    @Column(name = "menu_item_price")
    private String menuItemPrice;
    private int quantity;
    @Column(name = "total_price")
    private Double totalPrice;

    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OrdersModel order;
}
