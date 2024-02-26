package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.com.ecommers.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
}
