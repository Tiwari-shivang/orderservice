package com.example.orders.repositories;

import com.example.orders.models.StatusHistoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StatusRepository extends JpaRepository<StatusHistoryModel, UUID> {
}
