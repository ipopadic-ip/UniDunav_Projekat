package com.unidunav.administrator.service;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.model.Role;

import java.util.List;

public interface AdministratorService {
    UserDTO addRoleToUser(Long userId, String roleName);
    UserDTO removeRoleFromUser(Long userId, String roleName);
    UserDTO updateUserRoles(Long userId, UpdateRolesRequest request);
}

