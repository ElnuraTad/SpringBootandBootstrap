package com.peaksoft.service;

import com.peaksoft.model.Role;
import com.peaksoft.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public List<String> getRoleNamesToList() {
        List<Role> roles = roleRepository.findAll();
        List<String> names = new ArrayList<>();
        for (Role role : roles) {
            names.add(role.getRole());
        }
        return names;
    }

    @Override
    public Role getRoleByName(String name) {
        List<Role> roles = roleRepository.findAll();
        for (Role role : roles) {
            if (role.getRole().equals(name)) {
                return role;
            }
        }
        return null;
    }
}
