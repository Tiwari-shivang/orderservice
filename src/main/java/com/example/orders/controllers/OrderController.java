package com.example.orders.controllers;
import com.example.orders.dto.OrderReqDTO;
import com.example.orders.dto.OrderResponseDTO;
import com.example.orders.models.OrdersModel;
import com.example.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService service;
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderReqDTO order){
        OrderResponseDTO addedOrder = service.addOrder(order);
        return ResponseEntity.ok(addedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderDetails(@PathVariable("id") String id){
        OrderResponseDTO response = service.getOrderDetails(id);
        return ResponseEntity.ok(response);
    }
}
