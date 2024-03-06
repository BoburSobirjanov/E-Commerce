package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.com.ecommers.model.dto.order.OrderCreateDto;
import uz.com.ecommers.model.dto.order.OrderForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.OrderService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public StandardResponse<OrderForUser> save(
            @RequestBody OrderCreateDto orderCreateDto,
            Principal principal
            ){
        return orderService.save(orderCreateDto, principal);
    }
}
