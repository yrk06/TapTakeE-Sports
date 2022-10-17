package com.taptake.backend.controller;


import com.taptake.backend.DTO.OrganizationDTO;
import com.taptake.backend.model.Organization;
import com.taptake.backend.service.OrganizationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/org")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody OrganizationDTO organizationDTO) {

        List<Organization> orgList = organizationService.findAllByNomeOrg(organizationDTO.getNomeOrg());

        for (Organization org : orgList) {
            if (org.getNomeOrg().equals(organizationDTO.getNomeOrg()) && org.getLocalOrg().equals(organizationDTO.getLocalOrg()) && org.getDataCriacao().equals(organizationDTO.getDataCriacao())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }

        var newOrg = new Organization();
        BeanUtils.copyProperties(organizationDTO, newOrg);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationService.save(newOrg));
    }

    @GetMapping("/id")
    public ResponseEntity<?> findByID(@RequestParam String id) {
        Optional<Organization> optionalOrg = organizationService.findById(UUID.fromString(id));
        if (!optionalOrg.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(optionalOrg.get());
    }

    @GetMapping("/name")
    public ResponseEntity<?> findAllByNome(@RequestParam String nomeOrg) {
        return ResponseEntity.status(HttpStatus.OK).body(organizationService.findAllByNomeOrg(nomeOrg));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String id) {
        organizationService.deleteOne(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody OrganizationDTO orgDTO, @RequestParam String id) {

        Optional<Organization> optional_organization = organizationService.findById(UUID.fromString(id));

        if (!optional_organization.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Organization savedOrganization = optional_organization.get();

        if (!savedOrganization.getNomeOrg().equals(orgDTO.getNomeOrg())) {
            savedOrganization.setNomeOrg(orgDTO.getNomeOrg());
        }

        if (!savedOrganization.getLocalOrg().equals(orgDTO.getLocalOrg())) {
            savedOrganization.setLocalOrg(orgDTO.getLocalOrg());
        }

        if (!savedOrganization.getDataCriacao().equals(orgDTO.getDataCriacao())) {
            savedOrganization.setDataCriacao(orgDTO.getDataCriacao());
        }

        return ResponseEntity.status(HttpStatus.OK).body(organizationService.update(savedOrganization));

    }
}
