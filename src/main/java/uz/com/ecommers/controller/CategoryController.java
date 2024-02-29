package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.com.ecommers.model.dto.category.CategoryDto;
import uz.com.ecommers.model.dto.category.CategoryForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.CategoryService;

import java.security.Principal;

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
}
