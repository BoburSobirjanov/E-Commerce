package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.com.ecommers.repository.ProductCategoryRepository;
import uz.com.ecommers.repository.ProductRepository;
import uz.com.ecommers.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
}
