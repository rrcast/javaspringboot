package com.springproject.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.springproject.demo.entity.Order;
import com.springproject.demo.entity.OrderItem;
import com.springproject.demo.entity.Product;
import com.springproject.demo.repository.OrderItemRepository;
import com.springproject.demo.service.OrderItemService;
import com.springproject.demo.service.OrderService;
import com.springproject.demo.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderItemServiceTest {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderItemWithNullOrder() {
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(new Product());
        orderItem.setQuantity(2);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderItemService.createOrderItem(orderItem);
        });

        assertEquals("Order and Product must not be null", exception.getMessage());
    }
}