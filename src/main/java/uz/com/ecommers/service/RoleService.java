package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.com.ecommers.repository.RoleRepository;
import uz.com.ecommers.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
}
