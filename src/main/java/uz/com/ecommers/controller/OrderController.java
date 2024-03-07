package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.order.OrderCreateDto;
import uz.com.ecommers.model.dto.order.OrderForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.OrderService;

import java.security.Principal;
import java.util.UUID;

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


    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public StandardResponse<String> delete(
            @PathVariable UUID id,
            Principal principal
            ){
      return  orderService.delete(id, principal);
    }
}
