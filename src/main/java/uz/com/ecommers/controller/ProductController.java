package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.product.ProductCreateDto;
import uz.com.ecommers.model.dto.product.ProductForUser;
import uz.com.ecommers.model.entity.product.ProductEntity;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.ProductService;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public StandardResponse<ProductForUser> save(
            @RequestBody ProductCreateDto productCreateDto,
            Principal principal
            ){
       return productService.save(productCreateDto,principal);
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public StandardResponse<String> delete(
            @PathVariable UUID  id,
            Principal principal
            ){
        return productService.deleteById(id, principal);
    }

    @PostMapping("/get-by-category")
    public List<ProductForUser> getByCategory(
            @RequestParam String category
    ){
        return productService.getByCategory(category);
    }

    @GetMapping("/get-all")
    public List<ProductForUser> getAll(){
        return productService.getAll();
    }
}
