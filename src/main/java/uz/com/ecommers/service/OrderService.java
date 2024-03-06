package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.com.ecommers.model.dto.order.OrderCreateDto;
import uz.com.ecommers.model.dto.order.OrderForUser;
import uz.com.ecommers.model.entity.order.OrderEntity;
import uz.com.ecommers.model.entity.order.OrderStatus;
import uz.com.ecommers.model.entity.order.PaymentMethod;
import uz.com.ecommers.model.entity.product.ProductEntity;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.repository.OrderRepository;
import uz.com.ecommers.repository.ProductRepository;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;

    public StandardResponse<OrderForUser> save(OrderCreateDto orderCreateDto, Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        ProductEntity product = productRepository.getProductEntityById(UUID.fromString(orderCreateDto.getProductId()));
        OrderEntity order = OrderEntity.builder().cost(product.getPrice()* orderCreateDto.getProductCount())
                .productCount(orderCreateDto.getProductCount()).createdBy(user.getId()).status(OrderStatus.PROGRESS)
                .paymentMethod(PaymentMethod.valueOf(orderCreateDto.getPaymentMethod())).productId(UUID.fromString(orderCreateDto.getProductId()))
                        .deliveredTime(LocalDateTime.now().plusDays(1)).build();
        orderRepository.save(order);
        OrderForUser orderForUser = modelMapper.map(order, OrderForUser.class);
        return StandardResponse.<OrderForUser>builder()
                .status(Status.SUCCESS)
                .message("Order created!")
                .data(orderForUser)
                .build();
    }
}
