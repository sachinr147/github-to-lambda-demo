package org.example.controller;


import org.example.entity.Order;
import org.example.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderRepo orderRepo;

//    @GetMapping("/fetchorder")
//    public ResponseEntity<String> getOrder(){
//      return ResponseEntity.ok("Order placed successfully!");
//    }
    @GetMapping(path = "/fetchorder",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> allOrders = orderRepo.findAll();
            return ResponseEntity.ok(allOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    @PostMapping(path = "/order",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> placeOrder(@RequestBody Order order) {

        try {
            String orderId = UUID.randomUUID().toString();
            order.setOrderId(orderId);
            orderRepo.save(order);
            return ResponseEntity.ok("Order placed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to place order: " + e.getMessage());
        }
    }
}
