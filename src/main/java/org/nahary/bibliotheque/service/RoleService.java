package org.nahary.bibliotheque.service;

import org.nahary.bibliotheque.entity.Role;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    ResponseEntity<Role> getRoleById(Long id);

    Role createRole(Role role);

    ResponseEntity<Role> updateRole(Long id, Role updatedRole);

    ResponseEntity<Void> deleteRole(Long id);
}
