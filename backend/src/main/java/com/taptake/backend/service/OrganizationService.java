package com.taptake.backend.service;


import com.taptake.backend.model.Organization;
import com.taptake.backend.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional
    public Organization save(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Optional<Organization> findById(UUID id){
        return organizationRepository.findById(id);
    }

    public List<Organization> findAllByNomeOrg(String nomeOrg) {
        return organizationRepository.findAllByNomeOrg(nomeOrg);
    }

    public List<Organization> findAll() {
        return organizationRepository.findAll();
    }

    public void deleteOne(UUID id) {
        organizationRepository.deleteById(id);
    }

    public Organization update(Organization organization) {
        return organizationRepository.save(organization);
    }
}
