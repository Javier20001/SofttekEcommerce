package com.example.ecommers.repository;

import com.example.ecommers.model.DirEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface I_DirRepository extends JpaRepository<DirEntity, Long> {
}
