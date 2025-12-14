package com.circuitbreaker.user_service.controller;

import com.circuitbreaker.user_service.dto.OrderDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/user-service")
public class UserServiceController {

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    public static final String USER_SERVICE = "UserService";
    private static final String BASEURL = "http://localhost:8070/orders";

    @GetMapping("/displayOrders")
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "getAllAvailableProducts")
    public List<OrderDTO> displayOrders(
            @RequestParam(value = "category", required = false) String category) {

        String url = category == null ? BASEURL : BASEURL + "/" + category;

        ResponseEntity<List<OrderDTO>> response =
                restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<OrderDTO>>() {}
                );

        return response.getBody();
    }

    // ✅ Correct fallback method
    public List<OrderDTO> getAllAvailableProducts(String category, Exception e) {
        System.out.println("Fallback executed due to: " + e.getMessage());

        return Stream.of(
                new OrderDTO(119, "LED TV", "electronics", "white", 45000),
                new OrderDTO(345, "Headset", "electronics", "black", 7000),
                new OrderDTO(475, "Sound bar", "electronics", "black", 13000)
        ).collect(Collectors.toList());
    }
}
