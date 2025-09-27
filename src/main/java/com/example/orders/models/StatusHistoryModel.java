package com.example.orders.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_status_history")
public class StatusHistoryModel {
    @Id
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String previous_status, new_status, changed_by;
    private Timestamp timestamp;
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private OrdersModel order;
}
