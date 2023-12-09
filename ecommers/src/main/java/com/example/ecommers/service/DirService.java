package com.example.ecommers.service;

import com.example.ecommers.model.DirEntity;
import com.example.ecommers.repository.I_DirRepository;
import com.example.ecommers.serviceInterface.I_DirService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirService implements I_DirService {

     private final I_DirRepository dirRepository;
    @Override
    public Optional<DirEntity> getDirById(Long id) {
        try{
            Optional<DirEntity> optDir = dirRepository.findById(id);
            if(optDir.isPresent()){
                return optDir;
            }else{
                throw new RuntimeException("Direction do not exist");
            }
        }catch(Exception e){
            throw new RuntimeException("Error while searching direction");
        }
    }

    @Override
    public void saveDir(DirEntity dir) {
        try{
            if(dirRepository.findById(dir.getId()).isPresent()){
                throw new RuntimeException("Direction already exist");
            }
            dirRepository.save(dir);
        }catch(Exception e){
            throw new RuntimeException("Error while saving direction");
        }
    }
}
