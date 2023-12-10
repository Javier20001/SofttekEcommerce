package com.example.ecommers;

import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.RolesName;
import com.example.ecommers.model.UserEntity;
import com.example.ecommers.repository.I_UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserInitializer implements ApplicationRunner{

    private final I_UserRepository i_userRepository;



    private final PasswordEncoder pass;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(i_userRepository.count()==0){
            //i_userRepository.save(new UserEntity(1L,"testUser", "123@example.com.ar", pass.encode("password"),  "resetToken", LocalDateTime.now().plusDays(1), List.of(new RoleEntity(1L, RolesName.USER)) , true));
            i_userRepository.save(new UserEntity(2L,"testUser2", "1234@example.com.ar", pass.encode("password"), "resetToken", LocalDateTime.now().plusDays(1), List.of(new RoleEntity(2L, RolesName.ADMIN)) , true));
        }
    }
}
