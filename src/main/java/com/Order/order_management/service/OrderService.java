package com.Order.order_management.service;

import com.Order.order_management.dto.OrderRequest;
import com.Order.order_management.dto.OrderResponse;
import com.Order.order_management.entity.*;
import com.Order.order_management.exception.ProductNotExistException;
import com.Order.order_management.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
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

        if(Email==null){
            log.info("Email is having problem");
            return null;

        }
        Optional<Product> temp = productRepository.findById(orderRequest.getId());
        if(temp.isEmpty()) {
            throw new ProductNotExistException("This Product does not Exist");

        }


        Optional<Inventory> inventoryDetail = inventoryRepository.findByProduct(temp.get());
        if(inventoryDetail.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderResponse(null,"Not Avialbale in the inventory"));
        Inventory inv=inventoryDetail.get();
        if(inv.getQuantity()<orderRequest.getQuantity()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new OrderResponse(null,"Requested Quantity Exceeded"));
        }
        inv.setQuantity(inventoryDetail.get().getQuantity()-orderRequest.getQuantity());


        inventoryRepository.save(inv);
        Optional<User> user = userRepository.findByEmail(Email);
        if(user.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new OrderResponse(null,"user nhi milla"));
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

    public ResponseEntity<Order> listOrder() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        log.info(email);
        Optional<User> user = userRepository.getUserByEmail(email);
        if(user.isEmpty())return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Order());
        Long id= user.get().getId();
        Order order = orderRepository.getReferenceById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);

    }
}
