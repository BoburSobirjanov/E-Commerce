package uz.com.ecommers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.com.ecommers.exception.RequestValidationException;
import uz.com.ecommers.model.dto.user.LoginDto;
import uz.com.ecommers.model.dto.user.UserCreateDto;
import uz.com.ecommers.response.JwtResponse;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public StandardResponse<JwtResponse> signUp(
            @RequestBody UserCreateDto userCreateDto,
            BindingResult bindingResult
    ) throws RequestValidationException {
        if (bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            throw new RequestValidationException(allErrors);
        }
        return userService.signUp(userCreateDto);
    }

    @PostMapping("/sign-in")
    public StandardResponse<JwtResponse> login(
            @RequestBody LoginDto loginDto
            ){
        return userService.login(loginDto);
    }
}
