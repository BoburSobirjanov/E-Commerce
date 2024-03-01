package uz.com.ecommers.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.com.ecommers.exception.AuthenticationFailedException;
import uz.com.ecommers.exception.DataNotFoundException;
import uz.com.ecommers.exception.NotAcceptableException;
import uz.com.ecommers.exception.UserBadRequestException;
import uz.com.ecommers.model.dto.user.LoginDto;
import uz.com.ecommers.model.dto.user.UserCreateDto;
import uz.com.ecommers.model.dto.user.UserForUser;
import uz.com.ecommers.model.entity.user.Gender;
import uz.com.ecommers.model.entity.user.UserEntity;
import uz.com.ecommers.model.entity.user.UserRole;
import uz.com.ecommers.repository.UserRepository;
import uz.com.ecommers.response.JwtResponse;
import uz.com.ecommers.response.StandardResponse;
import uz.com.ecommers.response.Status;
import uz.com.ecommers.service.auth.JwtService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public StandardResponse<JwtResponse> signUp(UserCreateDto userCreateDto){
       checkUserEmailAndPhoneNumber(userCreateDto.getEmail(),userCreateDto.getPhoneNumber());
       UserEntity userEntity = modelMapper.map(userCreateDto, UserEntity.class);
       userEntity.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
       userEntity.setRoles(UserRole.USER);
       userEntity.setFullName(userCreateDto.getFullName());
       userEntity.setEmail(userCreateDto.getEmail());
       userEntity.setGender(Gender.valueOf(userCreateDto.getGender()));
       userEntity.setPhoneNumber(userCreateDto.getPhoneNumber());
       userEntity=userRepository.save(userEntity);
       String accessToken = jwtService.generateAccessToken(userEntity);
       String refreshToken = jwtService.generateRefreshToken(userEntity);
       UserForUser user = modelMapper.map(userEntity, UserForUser.class);
       JwtResponse jwtResponse = JwtResponse.builder()
               .accessToken(accessToken)
               .refreshToken(refreshToken)
               .user(user)
               .build();
       return StandardResponse.<JwtResponse>builder()
               .status(Status.SUCCESS)
               .message("Successfully signed Up")
               .data(jwtResponse)
               .build();
    }

    private void checkUserEmailAndPhoneNumber(String email, String phoneNumber) {
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity!=null){
                 throw new UserBadRequestException("Email has already exist!");
        }
        if (userRepository.findUserEntityByPhoneNumber(phoneNumber).isPresent()){
            throw new UserBadRequestException("Number has already exist!");
        }
    }

    public StandardResponse<JwtResponse> login(LoginDto loginDto){
       UserEntity userEntity = userRepository.findUserEntityByEmail(loginDto.getEmail());
        if (userEntity == null){
            throw new DataNotFoundException("User not found!");
        }
       if (passwordEncoder.matches(loginDto.getPassword(), userEntity.getPassword())){
          String accessToken= jwtService.generateAccessToken(userEntity);
          String refreshToken= jwtService.generateRefreshToken(userEntity);
          UserForUser user = modelMapper.map(userEntity, UserForUser.class);
          JwtResponse jwtResponse= JwtResponse.builder()
                  .accessToken(accessToken)
                  .refreshToken(refreshToken)
                  .user(user)
                  .build();
          return StandardResponse.<JwtResponse>builder()
                  .status(Status.SUCCESS)
                  .message("Sign in successfully!")
                  .data(jwtResponse)
                  .build();
       }
       else{
           throw new AuthenticationFailedException("Something error during signed in!");
       }
    }

    public StandardResponse<UserForUser> assignEmployer(String email){
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity==null){
            throw new DataNotFoundException("User not found!");
        }
        userEntity.setRoles(UserRole.EMPLOYER);
        userRepository.save(userEntity);
        UserForUser user = modelMapper.map(userEntity, UserForUser.class);
        return StandardResponse.<UserForUser>builder()
                .status(Status.SUCCESS)
                .message("Role changed!")
                .data(user)
                .build();
    }

    public StandardResponse<UserForUser> assignToAdmin(String email){
        UserEntity userEntity = userRepository.findUserEntityByEmail(email);
        if (userEntity==null){
            throw new DataNotFoundException("This user not found!");
        }
        userEntity.setRoles(UserRole.ADMIN);
        userRepository.save(userEntity);
        UserForUser user = modelMapper.map(userEntity, UserForUser.class);
        return StandardResponse.<UserForUser>builder()
                .status(Status.SUCCESS)
                .message("Role changed!")
                .data(user)
                .build();
    }
    public StandardResponse<UserForUser> removeAdmin(String email){
        UserEntity user = userRepository.findUserEntityByEmail(email);
        if (user==null){
            throw new DataNotFoundException("User not found!");
        }
        if (user.getRoles()==UserRole.ADMIN){
        user.setRoles(UserRole.EMPLOYER);
        userRepository.save(user);
        UserForUser userForUser = modelMapper.map(user,UserForUser.class);
        return StandardResponse.<UserForUser>builder()
                .status(Status.SUCCESS)
                .message("Role changed successfully!")
                .data(userForUser)
                .build();
        }
        throw new DataNotFoundException("This user is not ADMIN now!");
    }

   public StandardResponse<String> deleteUser(String email, Principal principal){
        UserEntity userEntity = userRepository.findUserEntityByEmail(principal.getName());
        UserEntity user = userRepository.findUserEntityByEmail(email);
        if (user==null){
            throw new DataNotFoundException("User not found!");
        }
        if (user.getRoles()==UserRole.USER || user.getRoles()==UserRole.EMPLOYER){
            user.setDeleted(true);
            user.setDeletedBy(userEntity.getId());
            user.setDeletedTime(LocalDateTime.now());
            userRepository.save(user);
            return StandardResponse.<String>builder()
                    .status(Status.SUCCESS)
                    .message("User deleted!")
                    .data("DELETED SUCCESSFULLY")
                    .build();
        }
       throw new NotAcceptableException("Can not delete this user!");
   }

   public StandardResponse<UserForUser> getUserById(UUID id){
        Optional<UserEntity> userEntity= userRepository.findById(id);
        UserForUser user = modelMapper.map(userEntity, UserForUser.class);
        return StandardResponse.<UserForUser>builder()
                .status(Status.SUCCESS)
                .message("This is user")
                .data(user)
                .build();
   }

}
