package com.taptake.backend;

import com.taptake.backend.DTO.OrganizationDTO;
import com.taptake.backend.controller.OrganizationController;
import com.taptake.backend.model.Organization;
import com.taptake.backend.service.OrganizationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = OrganizationController.class)
public class OrganizationControllerTests {

    @MockBean
    OrganizationService org_service;

    @Autowired
    @InjectMocks
    OrganizationController org_controller;


    @Test
    void saveValidOrganization() {
        Mockito.when(org_service.findAllByNomeOrg(anyString())).thenReturn(new ArrayList<>());

        OrganizationDTO org_test = new OrganizationDTO();
        org_test.setNomeOrg("teste");
        org_test.setLocalOrg("Curitiba");
        org_test.setDataCriacao(new Date(2002, Calendar.JANUARY, 12));

        ResponseEntity<?> re = org_controller.save(org_test);
        assertEquals(HttpStatus.CREATED, re.getStatusCode());

    }

    @Test
    void saveInvalidOrganization() {
        Organization org_test = new Organization();
        org_test.setNomeOrg("teste");
        org_test.setLocalOrg("Curitiba");
        org_test.setDataCriacao(new Date(2002, Calendar.JANUARY, 12));

        Mockito.when(org_service.findAllByNomeOrg(anyString())).thenReturn(new ArrayList<>() {{
            add(org_test);
        }});

        OrganizationDTO org_test2 = new OrganizationDTO();
        org_test2.setNomeOrg("teste");
        org_test2.setLocalOrg("Curitiba");
        org_test2.setDataCriacao(new Date(2002, Calendar.JANUARY, 12));

        ResponseEntity<?> re = org_controller.save(org_test2);
        assertEquals(HttpStatus.CONFLICT, re.getStatusCode());
    }

    @Test
    void recoverOrgValid() {
        UUID validOrg = UUID.randomUUID();
        Mockito.when(org_service.findById(validOrg)).thenReturn(Optional.of(new Organization()));
        ResponseEntity<?> re = org_controller.findByID(validOrg.toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void recoverOrgInvalid() {
        Mockito.when(org_service.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = org_controller.findByID(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }

    @Test
    void updateValidOrg() {
        UUID validOrg = UUID.randomUUID();
        Organization org = new Organization();
        org.setNomeOrg("teste");
        org.setLocalOrg("Curitiba");
        org.setDataCriacao(new Date(2002, Calendar.JANUARY, 12));
        Mockito.when(org_service.findById(any(UUID.class))).thenReturn(Optional.of(org));
        OrganizationDTO testOrg = new OrganizationDTO();
        testOrg.setNomeOrg("test");
        testOrg.setLocalOrg("test local");
        testOrg.setDataCriacao(new Date(2022, Calendar.JANUARY, 17));
        ResponseEntity<?> re = org_controller.update(testOrg, validOrg.toString());
        assertEquals(HttpStatus.OK, re.getStatusCode());
    }

    @Test
    void updateInvalidOrg() {
        Mockito.when(org_service.findById(any(UUID.class))).thenReturn(Optional.empty());
        OrganizationDTO testOrg = new OrganizationDTO();
        testOrg.setNomeOrg("test");
        testOrg.setLocalOrg("test local");
        ResponseEntity<?> re = org_controller.update(testOrg, UUID.randomUUID().toString());
        assertEquals(HttpStatus.NOT_FOUND, re.getStatusCode());
    }


    @Test
    void deleteOrg() {
        Mockito.when(org_service.findById(any(UUID.class))).thenReturn(Optional.empty());
        ResponseEntity<?> re = org_controller.delete(UUID.randomUUID().toString());
        assertEquals(HttpStatus.NO_CONTENT, re.getStatusCode());
    }

}