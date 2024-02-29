package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.com.ecommers.exception.NotAcceptableException;
import uz.com.ecommers.model.dto.category.CategoryDto;
import uz.com.ecommers.model.dto.category.CategoryForUser;
import uz.com.ecommers.model.entity.product.ProductCategory;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.repository.CategoryRepository;
import uz.com.ecommers.repository.ProductRepository;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public StandardResponse<CategoryForUser> save(CategoryDto categoryDto, Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        ProductCategory category = categoryRepository.findByName(categoryDto.getName());
        if (category!=null){
            throw new NotAcceptableException("This category has already exist!");
        }
        ProductCategory productCategory = ProductCategory.builder()
                .name(categoryDto.getName())
                .createdBy(user.getId())
                .build();
        categoryRepository.save(productCategory);
        CategoryForUser categoryForUser = modelMapper.map(productCategory, CategoryForUser.class);
        return StandardResponse.<CategoryForUser>builder()
                .status(Status.SUCCESS)
                .message("Category saved!")
                .data(categoryForUser)
                .build();
    }
}
