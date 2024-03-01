package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.com.ecommers.model.dto.user.UserForUser;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.UserService;

import java.security.Principal;
import java.util.UUID;

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
    @PostMapping("/assign-employer")
    @PreAuthorize("hasRole('OWNER')")
    public StandardResponse<UserForUser> assignToAdmin(
            @RequestParam String email
    ){
        return userService.assignToAdmin(email);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('ADMIN') or hasRole('OWNER')")
    public StandardResponse<String> deleteUser(
            @RequestParam String email,
            Principal principal
    ){
        return userService.deleteUser(email,principal);
    }

    @PostMapping("/remove-admin")
    @PreAuthorize("hasRole('OWNER')")
    public StandardResponse<UserForUser> removeAdmin(
            @RequestParam String email
    ){
        return userService.removeAdmin(email);
    }

    @GetMapping("/{id}/get-by-id")
    public StandardResponse<UserForUser> getById(
            @PathVariable UUID id
            ){
        return userService.getUserById(id);
    }

}
