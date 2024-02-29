package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.com.ecommers.model.dto.product.ProductCreateDto;
import uz.com.ecommers.model.dto.product.ProductForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.ProductService;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('EMPLOYER')")
    public StandardResponse<ProductForUser> save(
            @RequestBody ProductCreateDto productCreateDto,
            Principal principal
            ){
       return productService.save(productCreateDto,principal);
    }
}
