package com.example.ecommers;

import com.example.ecommers.model.RoleEntity;
import com.example.ecommers.model.RolesName;
import com.example.ecommers.repository.I_RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoleInitializer implements ApplicationRunner {

    private final I_RoleRepository i_roleRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (i_roleRepository.count() == 0) {
            i_roleRepository.save(new RoleEntity(1l, RolesName.USER));
            i_roleRepository.save(new RoleEntity(2l, RolesName.ADMIN));
        }
    }
}
