package com.Order.order_management.service;

import com.Order.order_management.dto.OrderRequest;
import com.Order.order_management.dto.OrderResponse;
import com.Order.order_management.entity.*;
import com.Order.order_management.exception.ProductNotExistException;
import com.Order.order_management.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OrderService {
    private final InventoryRepository inventoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;

    public OrderService(ProductRepository productRepository, UserRepository userRepository, InventoryRepository inventoryRepository, OrderRepository orderRepository, OrderItemsRepository orderItemsRepository){
        this.productRepository=productRepository;
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
        this.orderRepository = orderRepository;
        this.orderItemsRepository = orderItemsRepository;
    }

    public ResponseEntity<OrderResponse> order(OrderRequest orderRequest) {
        String Email= SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Product> temp = productRepository.findById(orderRequest.getId());
        if(temp.isEmpty()) {
            throw new ProductNotExistException("This Product does not Exist");

        }


        Optional<Inventory> inventoryDetail = inventoryRepository.findByProduct(temp.get());

        if(inventoryDetail.get().getQuantity()<orderRequest.getQuantity()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OrderResponse(null,"Requested Quantity Exceeded"));
        }
        inventoryDetail.get().setQuantity(inventoryDetail.get().getQuantity()-orderRequest.getQuantity());
        Inventory inv=inventoryDetail.get();
        inventoryRepository.save(inv);
        Optional<User> user = userRepository.findByEmail(Email);
        User currentUser = user.get();
        Product product=temp.get();
        Order order=new Order();
        order.setUser(currentUser);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
       Order o= orderRepository.save(order);

        OrderItem orderItem= new OrderItem();
        orderItem.setProduct(product);
        orderItem.setQuantity(orderRequest.getQuantity());
        orderItem.setOrder(order);
        orderItemsRepository.save(orderItem);
        return ResponseEntity.status(HttpStatus.OK).body(new OrderResponse(o.getId(),"Order Placed"));

    }
}
