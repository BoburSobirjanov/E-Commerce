package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.user.UserForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/assign-employer")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public StandardResponse<UserForUser> assignEmployer(
            @RequestParam String email
    ){
        return userService.assignEmployer(email);
    }
}
