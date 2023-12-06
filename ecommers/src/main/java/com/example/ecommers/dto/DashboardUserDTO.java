package com.example.ecommers.dto;

import com.example.ecommers.model.RoleEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardUserDTO {
    private Long id;
    private String userName;
    private String email;
    private List<RoleEntity> roles;
    private Boolean status;
}
