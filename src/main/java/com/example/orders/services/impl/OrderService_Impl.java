package com.example.orders.services.impl;


import com.example.orders.dto.OrderItemDTO;
import com.example.orders.dto.OrderReqDTO;
import com.example.orders.dto.OrderResponseDTO;
import com.example.orders.models.OrderItemModel;
import com.example.orders.models.OrdersModel;
import com.example.orders.repositories.OrderRepository;
import com.example.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService_Impl implements OrderService {
    @Autowired
    private OrderRepository repo;

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
        // ab entity model ko response dto me convert krunga.
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

//    @Override
//    public OrderResponseDTO getOrderDetails(String id){
//        OrdersModel order = repo.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Not found"));
//        ArrayList<OrderItemDTO> items = order.getOrderItems().stream().map(item -> new OrderItemDTO(item.getId().toString(), item.getMenuItemId(), item.getMenuItemName(), item.getMenuItemPrice(), item.getQuantity(), item.getTotalPrice())).collect(Collectors.toCollection(ArrayList::new));
//        return new OrderResponseDTO(order.getId().toString(), order.getUser_id().toString(), order.getRestaurant_address_id().toString(), order.getCart_id().toString(), order.getDelivery_id().toString(), order.getPayment_id().toString(), order.getOrder_status(), order.getPayment_status(), order.getSpecial_instructions(), order.getTotal_amount(), items, order.getCreated_at(), order.getUpdated_at());
//    }
}
