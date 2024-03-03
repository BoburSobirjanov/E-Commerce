package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import uz.com.ecommers.exception.DataNotFoundException;
import uz.com.ecommers.exception.NotAcceptableException;
import uz.com.ecommers.exception.RequestValidationException;
import uz.com.ecommers.model.dto.product.ProductCreateDto;
import uz.com.ecommers.model.dto.product.ProductForUser;
import uz.com.ecommers.model.entity.product.ProductCategory;
import uz.com.ecommers.model.entity.product.ProductEntity;
import uz.com.ecommers.model.entity.product.ProductState;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.repository.CategoryRepository;
import uz.com.ecommers.repository.ProductRepository;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;


    public StandardResponse<ProductForUser> save(ProductCreateDto productCreateDto, Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        ProductCategory category = categoryRepository.findByName(productCreateDto.getCategory());
        checkHasProduct(productCreateDto.getName(), productCreateDto.getCategory(), productCreateDto.getState());
        ProductEntity product = modelMapper.map(productCreateDto, ProductEntity.class);
        if (category.equals(productCreateDto.getCategory())){
              throw new DataNotFoundException("Category not found!");
        }
        try {
            product.setState(ProductState.valueOf(productCreateDto.getState()));
        }catch (Exception e){
            throw new NotAcceptableException("Invalid state!");
        }
        product.setCategory(productCreateDto.getCategory());
        product.setCount(productCreateDto.getCount());
        product.setColor(productCreateDto.getColor());
        product.setCreatedBy(user.getId());
        product.setDescription(productCreateDto.getDescription());
        product.setPrice(productCreateDto.getPrice());
        product.setName(productCreateDto.getName());
        productRepository.save(product);
        ProductForUser productForUser = modelMapper.map(product, ProductForUser.class);
        return StandardResponse.<ProductForUser>builder()
                .status(Status.SUCCESS)
                .message("Product saved successfully!")
                .data(productForUser)
                .build();
    }

    public void checkHasProduct(String name, String category, String state){
        ProductEntity product = productRepository.findProductEntityByNameAndCategoryAndState(name,category, ProductState.valueOf(state));
        if (product!=null){
            throw new NotAcceptableException("Product has already exist!");
        }
    }

    public StandardResponse<String> deleteById(UUID id,Principal principal){
        UserEntity user = userRepository.findUserEntityByEmail(principal.getName());
        ProductEntity product = productRepository.getProductEntityById(id);
        if (product==null){
            throw new DataNotFoundException("Product not found!");
        }
        product.setDeleted(true);
        product.setDeletedTime(LocalDateTime.now());
        product.setDeletedBy(user.getId());
        productRepository.save(product);
        return StandardResponse.<String>builder()
                .status(Status.SUCCESS)
                .message("Product deleted!")
                .data("DELETED SUCCESSFULLY")
                .build();
    }

    public List<ProductForUser> getByCategory(String category){
        List<ProductForUser> productEntities = productRepository.findProductEntitiesByCategory(category);
        if (productEntities==null){
            throw new DataNotFoundException("Product not found!");
        }
          return productEntities;
    }

    public List<ProductForUser> getAll(){
        List<ProductForUser> productEntities = productRepository.getAll();
        if (productEntities==null){
            throw new DataNotFoundException("Product not found!");
        }
        return productEntities;
    }
}
