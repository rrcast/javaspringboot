package com.springproject.demo.service;


import com.springproject.demo.entity.Order;
import com.springproject.demo.entity.OrderItem;
import com.springproject.demo.entity.Product;
import com.springproject.demo.repository.OrderItemRepository;
import com.springproject.demo.repository.OrderRepository;
import com.springproject.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderItemService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    public List<OrderItem> getAllOrderItems() {
        return orderItemRepository.findAll();
    }

    public OrderItem getOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    public OrderItem createOrderItem(OrderItem orderItem) {
        if (orderItem.getOrder() == null || orderItem.getProduct() == null) {
            throw new IllegalArgumentException("Order and Product must not be null");
        }

        Order order = orderRepository.findById(orderItem.getOrder().getId())
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        Product product = productRepository.findById(orderItem.getProduct().getId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        orderItem.setOrder(order);
        orderItem.setProduct(product);
        return orderItemRepository.save(orderItem);
    }

    public OrderItem updateOrderItem(Long id, OrderItem orderItemDetails) {
        return orderItemRepository.findById(id).map(orderItem -> {
            orderItem.setQuantity(orderItemDetails.getQuantity());
            orderItem.setProduct(orderItemDetails.getProduct());
            orderItem.setOrder(orderItemDetails.getOrder());
            return orderItemRepository.save(orderItem);
        }).orElse(null);
    }

    public boolean deleteOrderItem(Long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}