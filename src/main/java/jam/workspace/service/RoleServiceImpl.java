package jam.workspace.service;

import jam.workspace.dao.RoleDao;
import jam.workspace.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Override
    public List<String> getRoleNamesToList() {
        List<Role> roles = roleDao.findAll();
        List<String> names = new ArrayList<>();
        for (Role role : roles) {
            names.add(role.getRole());
        }
        return names;
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = roleDao.findAll();
        for (Role role : roles) {
            if (role.getRole().equals(name)) {
                return role;
            }
        }
        return null;
    }
}
