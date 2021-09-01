package jam.workspace.service;

import jam.workspace.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RoleService {

    List<Role> getAllRoles();

    List<String> getRoleNamesToList();

    Role getRoleByName(String name);
}
