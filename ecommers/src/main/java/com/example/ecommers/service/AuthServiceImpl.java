package com.example.ecommers.service;

import com.example.ecommers.dto.LoginResponseDTO;
import com.example.ecommers.dto.LoginResponseUserDTO;
import com.example.ecommers.dto.RegisterUserDTO;
import com.example.ecommers.dto.ResetPasswordUserDTO;
import com.example.ecommers.exception.CustomHandler;
import com.example.ecommers.exception.UserAlrdyExist;
import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import com.example.ecommers.security.JwtUtil;
import com.example.ecommers.serviceInterface.I_AuthService;
import com.example.ecommers.serviceInterface.I_RoleService;
import com.example.ecommers.serviceInterface.I_UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.HandlerMethodValidationException;


import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of the {@link I_UserService} interface, providing authentication
 * and user-related operations for the application.
 *
 * @Service Indicates that this class is a Spring service component.
 */
@Service
public class AuthServiceImpl implements I_AuthService {

    /**
     * Autowired field for the user repository, providing data access methods.
     */
    @Autowired
    private I_UserRepository userRepository;

    /**
     * Autowired field for the ModelMapper, facilitating object mapping.
     */
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Autowired field for the role service, providing role-related business logic.
     */
    @Autowired
    private I_RoleService roleService;

    /**
     * Autowired field for the password encoder, providing password encryption functionality.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * Saves a new user based on the provided RegisterUserDTO.
     *
     * @param user The DTO containing information for creating a new user.
     * @return The saved RegisterUserDTO.
     * @throws RuntimeException if a user with the same email already exists.
     */
    @Transactional
    @Override
    public RegisterUserDTO save(RegisterUserDTO user) throws RuntimeException {

        List<RoleEntity> userRoles = new ArrayList<>();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(setRole(user.getRoles()));
        userEntity.setStatus(true);

        if (userRepository.findByEmail(userEntity.getEmail()).isPresent()) {
            throw new UserAlrdyExist("This user email already exist");
        } else if(userRepository.findByUserName(userEntity.getUserName()).isPresent()){
            throw new UserAlrdyExist("This user name already exist");
        }else{
            try {
                userRepository.save(userEntity);
            } catch (CustomHandler e) {
                throw new RuntimeException("Error saving user on the database" + e.getMessage());
            }
        }
        return user;
    }

    /**
     * Retrieves a user by email, ensuring that the user is active.
     *
     * @param email The email of the user to retrieve.
     * @return An Optional containing the user entity if found and active.
     */
    @Override
    public Optional<UserEntity> findByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email).get();
        Optional<UserEntity> userEntity = null;

        if (user.getStatus()) {
            userEntity = userRepository.findByEmail(email);
        }
        return userEntity;
    }

    @Override
    public void resetRequest(String email) {
        Optional<UserEntity> optUser = userRepository.findByEmail(email);
        if(optUser.isPresent()){
            UserEntity user = optUser.get();
            user.setResetToken(UUID.randomUUID().toString());
            user.setExpirationDate(LocalDateTime.now().plusHours(2));
            userRepository.save(user);
            String resetLink = "http://localhost:5173/password/new/" + user.getResetToken();
            emailService.sendEmail(email, "Password Recovery", "Hello, this is your reset request email. To reset your password, visit  <a href='"+resetLink+"'>"+resetLink+"</a>");
        }else{
            System.out.println("error user not found");
        }
    }
    public void resetPassword(ResetPasswordUserDTO resetPasswordUserDTO){
        Optional<UserEntity> optUser = userRepository.findByToken(resetPasswordUserDTO.getResetToken());
        if(optUser.isPresent()){
            UserEntity user = optUser.get();
            user.setPassword(passwordEncoder.encode(resetPasswordUserDTO.getPassword()));
            userRepository.save(user);
        }else{
            System.out.println("error");
        }
    }


    /**
     * Sets roles for a user based on the provided role IDs.
     *
     * @param roles The list of role IDs to set for the user.
     * @return A list of Role entities corresponding to the provided role IDs.
     * @throws RuntimeException if there is an error adding roles to the user.
     */
    public List<RoleEntity> setRole(List<Long> roles) {
        List<RoleEntity> aux = new ArrayList<>();
        for (int i = 0; i < roles.size(); i++) {
            try {
                aux.add(this.roleService.getRoleById(roles.get(i)).get());
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error adding role to the user");
            }
        }
        return aux;
    }
    private Random random = new Random();
    /**
     * Generates a random long ID.
     *
     * @return A randomly generated long ID.
     */
    private long generateRandomId() {
        return random.nextLong();
    }

    public LoginResponseDTO generateToken(String email, String password, UserEntity user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        // Set the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        String token = jwtUtil.generateAccesToken(email);

        LoginResponseUserDTO loginResponseUserDTO = new LoginResponseUserDTO(
                user.getUserName(),
                user.getRoles().stream().map(role -> String.valueOf(role.getName())).toList()
        );
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(
                token,
                loginResponseUserDTO
        );

        return loginResponseDTO;
    }
}
