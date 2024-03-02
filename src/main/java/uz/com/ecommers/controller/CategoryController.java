package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.category.CategoryDto;
import uz.com.ecommers.model.dto.category.CategoryForUser;
import uz.com.ecommers.model.entity.product.ProductCategory;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.CategoryService;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public StandardResponse<CategoryForUser> save(
            @RequestBody CategoryDto categoryDto,
            Principal principal
            ){
        return categoryService.save(categoryDto,principal);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('OWNER') or hasRole('ADMIN')")
    public StandardResponse<String> delete(
            @RequestParam String name,
            Principal principal
    ){return categoryService.delete(name,principal);}

    @GetMapping("/get-all")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public List<ProductCategory> getAll(){
        return categoryService.getAll();
    }

    @PostMapping("/get-by-name")
    @PreAuthorize("hasRole('EMPLOYER') or hasRole('ADMIN')")
    public StandardResponse<CategoryForUser> getByName(
            @RequestParam String name
    ){
        return categoryService.getByName(name);

    }
}
