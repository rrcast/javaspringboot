package com.springproject.demo;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.springproject.demo.entity.Product;
import com.springproject.demo.repository.ProductRepository;
import com.springproject.demo.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(new BigDecimal("29.99"));

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
        assertEquals(new BigDecimal("29.99"), createdProduct.getPrice());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setId(1L);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById(1L);

        assertNotNull(foundProduct);
        assertEquals(1L, foundProduct.getId());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductByIdNotFound() {
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () -> {
            productService.getProductById(1L);
        });

        assertEquals("Product not found", exception.getMessage());
    }
}