package com.example.orders.repositories;

import com.example.orders.models.OrdersModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrdersModel, UUID> {
}
