package com.example.orders.services;

import com.example.orders.dto.OrderReqDTO;
import com.example.orders.dto.OrderResponseDTO;
import com.example.orders.models.OrdersModel;

public interface OrderService {
    OrderResponseDTO addOrder(OrderReqDTO order);
//    OrderResponseDTO getOrderDetails(String id);
}
