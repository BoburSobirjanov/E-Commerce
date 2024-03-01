package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.com.ecommers.exception.DataNotFoundException;
import uz.com.ecommers.exception.NotAcceptableException;
import uz.com.ecommers.model.dto.category.CategoryDto;
import uz.com.ecommers.model.dto.category.CategoryForUser;
import uz.com.ecommers.model.entity.product.ProductCategory;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.repository.CategoryRepository;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
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

    public StandardResponse<String> delete(String name,Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        ProductCategory category = categoryRepository.findByName(name);
        if (category==null){
            throw new DataNotFoundException("Category not found!");
        }
        category.setDeleted(true);
        category.setDeletedBy(user.getId());
        category.setDeletedTime(LocalDateTime.now());
        categoryRepository.save(category);
        return StandardResponse.<String>builder()
                .status(Status.SUCCESS)
                .message("Deleted category")
                .data("DELETED SUCCESSFULLY!")
                .build();
    }
    public StandardResponse<CategoryForUser> getByName(String name){
        ProductCategory category = categoryRepository.findByName(name);
        if (category==null){
            throw new DataNotFoundException("Category not found!");
        }
        CategoryForUser categoryForUser = modelMapper.map(category, CategoryForUser.class);
        return StandardResponse.<CategoryForUser>builder()
                .status(Status.SUCCESS)
                .message("This is category!")
                .data(categoryForUser)
                .build();
    }

    public List<ProductCategory> getAll(){
        List<ProductCategory> productCategories = categoryRepository.getAll();
        if (productCategories==null){
            throw new DataNotFoundException("Categories not found");
        }
        return productCategories;
    }
}
