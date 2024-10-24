package com.springproject.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.springproject.demo.entity.Order;
import com.springproject.demo.repository.OrderRepository;
import com.springproject.demo.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order();
        order.setId(1L);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order createdOrder = orderService.createOrder(order);

        assertNotNull(createdOrder);
        assertEquals(1L, createdOrder.getId());
        verify(orderRepository, times(1)).save(order);
    }
}