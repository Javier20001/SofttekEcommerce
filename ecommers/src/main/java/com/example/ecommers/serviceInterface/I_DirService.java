package com.example.ecommers.serviceInterface;

import com.example.ecommers.model.DirEntity;

import java.util.Optional;

public interface I_DirService {
    Optional<DirEntity> getDirById(Long id);
    void saveDir(DirEntity dir);
}
