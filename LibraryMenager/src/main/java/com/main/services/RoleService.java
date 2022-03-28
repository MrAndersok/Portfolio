package com.comp.services;

import com.comp.models.Role;
import com.comp.models.User;
import com.comp.repository.RoleRepository;
import com.comp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    public List<Role> listAllRole() {
        return roleRepository.findAll();
    }

    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    public Role getRole(Integer id) {
        return roleRepository.findById(id).get();
    }

    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}

