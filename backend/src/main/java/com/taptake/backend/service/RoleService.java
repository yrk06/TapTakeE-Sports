package com.taptake.backend.service;

import com.taptake.backend.model.Role;
import com.taptake.backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Optional<Role> findById(Integer id) {
        return roleRepository.findById(id);
    }
}
