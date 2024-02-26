package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.com.ecommers.repository.ProductRepository;
import uz.com.ecommers.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
}
