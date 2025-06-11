package com.unidunav.administrator.service;
import com.unidunav.user.dto.UserDTO;
import com.unidunav.user.dto.request.UpdateRolesRequest;
import com.unidunav.user.model.Role;

import java.util.List;

import org.springframework.core.io.Resource;

public interface AdministratorService {
    UserDTO addRoleToUser(Long userId, String roleName);
    UserDTO removeRoleFromUser(Long userId, String roleName);
    UserDTO updateUserRoles(Long userId, UpdateRolesRequest request);
    
    public Resource exportStudentsToPdf() throws Exception;
    public Resource exportStudentsToXml() throws Exception;
    
    public Resource exportProfesorsToXml() throws Exception;
    public Resource exportProfesorsToPdf() throws Exception;
}

