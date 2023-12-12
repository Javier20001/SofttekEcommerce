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
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Implementation of the {@link I_AuthService} interface, providing authentication
 * and user-related operations for the application.
 *
 * @Service Indicates that this class is a Spring service component.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements I_AuthService {
    
    
    /**
    * Autowired field for the user repository, providing data access methods.
    */
    private final I_UserRepository userRepository;
    
    /**
    * Autowired field for the ModelMapper, facilitating object mapping.
    */
    private final ModelMapper modelMapper;
    
    /**
    * Autowired field for the role service, providing role-related business logic.
    */
    private final I_RoleService roleService;
    
    /**
    * Autowired field for the password encoder, providing password encryption functionality.
    */
    private final PasswordEncoder passwordEncoder;
    
    private final EmailService emailService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    /**
     * Saves a new user based on the provided RegisterUserDTO.
     *
     * @param user The DTO containing information for creating a new user.
     * @return The saved RegisterUserDTO.
     * @throws RuntimeException if a user with the same email already exists.
     */
    @Override
    public RegisterUserDTO save(RegisterUserDTO user) throws RuntimeException {
        List<RoleEntity> userRoles = new ArrayList<>();
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(setRole(user.getRoles()));
        userEntity.setStatus(true);

        Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(userEntity.getEmail());
        Optional<UserEntity> existingUserByUserName = userRepository.findByUserName(userEntity.getUserName());

        if (existingUserByEmail.isPresent()) {
            throw new UserAlrdyExist("This user email already exists");
        } else if (existingUserByUserName.isPresent()) {
            throw new UserAlrdyExist("This user name already exists");
        } else {
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
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        return userEntity.filter(UserEntity::getStatus);
    }

    /**
     * Initiates the password reset process by generating a reset token and sending an email to the user.
     *
     * @param email The email of the user requesting the password reset.
     */
    @Override
    public void resetRequest(String email) {
        Optional<UserEntity> optUser = userRepository.findByEmail(email);
        optUser.ifPresent(user -> {
            user.setResetToken(UUID.randomUUID().toString());
            user.setExpirationDate(LocalDateTime.now().plusHours(2));
            userRepository.save(user);
            String resetLink = "https://hardtek.vercel.app" +"/password/new/" + user.getResetToken();
            emailService.sendEmail(email, "Password Recovery", "Hello, this is your reset request email. To reset your password, visit  <a href='"+resetLink+"'>"+resetLink+"</a>");
        });
    }

    /**
     * Resets the password for a user based on the reset token.
     *
     * @param resetPasswordUserDTO The DTO containing the reset token and new password.
     */
    @Override
    public void resetPassword(ResetPasswordUserDTO resetPasswordUserDTO) {
        Optional<UserEntity> optUser = userRepository.findByToken(resetPasswordUserDTO.getResetToken());
        optUser.ifPresent(user -> {
            user.setPassword(passwordEncoder.encode(resetPasswordUserDTO.getPassword()));
            userRepository.save(user);
        });
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

    public LoginResponseDTO generateToken(String email, String password, UserEntity user) {
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


