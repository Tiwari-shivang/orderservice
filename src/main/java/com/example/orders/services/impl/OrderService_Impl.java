package com.example.orders.services.impl;


import com.example.orders.dto.OrderItemDTO;
import com.example.orders.dto.OrderReqDTO;
import com.example.orders.dto.OrderResponseDTO;
import com.example.orders.models.OrderItemModel;
import com.example.orders.models.OrdersModel;
import com.example.orders.models.StatusHistoryModel;
import com.example.orders.repositories.OrderRepository;
import com.example.orders.repositories.StatusRepository;
import com.example.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class OrderService_Impl implements OrderService {
    @Autowired
    private OrderRepository repo;

    @Autowired
    private StatusRepository statusRepo;

    @Override
    public OrderResponseDTO addOrder(OrderReqDTO orderReqDto){
        OrdersModel newOrder = new OrdersModel(orderReqDto);
        ArrayList<OrderItemModel> orders = new ArrayList<>();
        orderReqDto.getItems().forEach(itemDto -> {
            OrderItemModel itemModel = new OrderItemModel();
            itemModel.setMenuItemId(itemDto.getMenuItemId());
            itemModel.setMenuItemName(itemDto.getMenuItemName());
            itemModel.setMenuItemPrice(itemDto.getMenuItemPrice());
            itemModel.setQuantity(itemDto.getQuantity());
            itemModel.setTotalPrice(itemDto.getTotalPrice());
            itemModel.setOrder(newOrder);
            orders.add(itemModel);
        });
        newOrder.setOrderItems(orders);
        OrdersModel createdOrder = repo.save(newOrder);

        StatusHistoryModel history = new StatusHistoryModel();
        history.setOrder(createdOrder);
        history.setPrevious_status("N/A");
        history.setNew_status("PENDING");
        history.setChanged_by("User");
        history.setTimestamp(Timestamp.from(Instant.now()));
        statusRepo.save(history);

        ArrayList<OrderItemDTO> createItems = new ArrayList<>();
        createdOrder.getOrderItems().forEach(itemModel -> {
            OrderItemDTO itemDTO = new OrderItemDTO();
            itemDTO.setId(itemModel.getId().toString());
            itemDTO.setMenuItemId(itemModel.getMenuItemId());
            itemDTO.setMenuItemName(itemModel.getMenuItemName());
            itemDTO.setMenuItemPrice(itemModel.getMenuItemPrice());
            itemDTO.setQuantity(itemModel.getQuantity());
            itemDTO.setTotalPrice(itemModel.getTotalPrice());
            createItems.add(itemDTO);
        });
        return new OrderResponseDTO(createdOrder.getId().toString(), createdOrder.getUser_id().toString(), createdOrder.getRestaurant_address_id().toString(), createdOrder.getCart_id().toString(), null, null, createdOrder.getOrder_status(), createdOrder.getPayment_status(), createdOrder.getSpecial_instructions(), createdOrder.getTotal_amount(), createItems, createdOrder.getCreated_at(), createdOrder.getUpdated_at());
    }

    @Override
    public OrderResponseDTO getOrderDetails(String id){
        OrdersModel order = repo.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Not found"));
        ArrayList<OrderItemDTO> orderItems = new ArrayList<>();
        order.getOrderItems().forEach(item -> {
            orderItems.add(new OrderItemDTO(item.getId().toString(), item.getMenuItemId(), item.getMenuItemName(), item.getMenuItemName(), item.getQuantity(), item.getTotalPrice()));
        });
        OrderResponseDTO response = new OrderResponseDTO();
        response.setId(order.getId().toString());
        response.setUser_id(order.getUser_id().toString());
        response.setRestaurant_address_id(order.getRestaurant_address_id().toString());
        response.setCart_id(order.getCart_id().toString());
        response.setOrder_status(order.getOrder_status());
        response.setPayment_status(order.getPayment_status());
        response.setSpecial_instructions(order.getSpecial_instructions());
        response.setTotal_amount(order.getTotal_amount());
        response.setItems(orderItems);
        response.setCreated_at(order.getCreated_at());
        response.setUpdated_at(order.getUpdated_at());
        return response;
    }
}
