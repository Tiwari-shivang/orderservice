package com.example.orders.repositories;

import com.example.orders.models.OrderItemModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItemModel, UUID> {
}
