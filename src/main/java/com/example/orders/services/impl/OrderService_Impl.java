package com.example.orders.services.impl;


import com.example.orders.dto.OrderItemDTO;
import com.example.orders.dto.OrderReqDTO;
import com.example.orders.dto.OrderResponseDTO;
import com.example.orders.models.OrderItemModel;
import com.example.orders.models.OrdersModel;
import com.example.orders.repositories.OrderItemRepository;
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

    @Autowired
    private OrderItemRepository itemRepo;

    @Override
    public OrderResponseDTO addOrder(OrderReqDTO orderReqDto){
        OrdersModel newOrder = new OrdersModel(orderReqDto);
        OrdersModel createdOrder = repo.save(newOrder);

        ArrayList<OrderItemDTO> addedItems = new ArrayList<>();
        orderReqDto.getItems().forEach(itemDto -> {
            OrderItemModel itemModel = new OrderItemModel();
            itemModel.setMenuItemId(itemDto.getMenuItemId());
            itemModel.setMenuItemName(itemDto.getMenuItemName());
            itemModel.setMenuItemPrice(itemDto.getMenuItemPrice());
            itemModel.setQuantity(itemDto.getQuantity());
            itemModel.setTotalPrice(itemDto.getTotalPrice());
            itemModel.setOrder(createdOrder);
            OrderItemModel addedItem = itemRepo.save(itemModel);
            OrderItemDTO addedItemDTO = new OrderItemDTO(addedItem.getId().toString(), addedItem.getMenuItemId(), addedItem.getMenuItemName(), addedItem.getMenuItemPrice(), addedItem.getQuantity(), addedItem.getTotalPrice());
            addedItems.add(addedItemDTO);
        });
        return new OrderResponseDTO(createdOrder.getId().toString(), createdOrder.getUser_id().toString(), createdOrder.getRestaurant_address_id().toString(), createdOrder.getCart_id().toString(), null, null, createdOrder.getOrder_status(), createdOrder.getPayment_status(), createdOrder.getSpecial_instructions(), createdOrder.getTotal_amount(), addedItems, createdOrder.getCreated_at(), createdOrder.getUpdated_at());
    }

//    @Override
//    public OrderResponseDTO getOrderDetails(String id){
//        OrdersModel order = repo.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Not found"));
//        ArrayList<OrderItemDTO> items = order.getOrderItems().stream().map(item -> new OrderItemDTO(item.getId().toString(), item.getMenuItemId(), item.getMenuItemName(), item.getMenuItemPrice(), item.getQuantity(), item.getTotalPrice())).collect(Collectors.toCollection(ArrayList::new));
//        return new OrderResponseDTO(order.getId().toString(), order.getUser_id().toString(), order.getRestaurant_address_id().toString(), order.getCart_id().toString(), order.getDelivery_id().toString(), order.getPayment_id().toString(), order.getOrder_status(), order.getPayment_status(), order.getSpecial_instructions(), order.getTotal_amount(), items, order.getCreated_at(), order.getUpdated_at());
//    }
}
