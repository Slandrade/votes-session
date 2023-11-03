package com.slandrade.sicreditest.domain.associate.controller;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import com.slandrade.sicreditest.domain.associate.service.AssociateService;
import com.slandrade.sicreditest.domain.associate.dto.AssociateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/associates")
public class AssociateController {

    private final AssociateService associateService;

    @PostMapping("/")
    public ResponseEntity<Object> createAssociate(@RequestBody AssociateModel associateModel) {

        var associateCreated = associateService.createAssociate(associateModel);
        var associateDTO = AssociateDTO.convertToDTO(associateCreated);

        if (associateDTO.getName() == null || associateDTO.getCpf() == null) {
            return ResponseEntity.badRequest().body("Name and CPF are required");
        }

        return ResponseEntity.ok(associateDTO);
    }
}
