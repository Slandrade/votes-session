package com.slandrade.sicreditest.domain.associate.dto;

import com.slandrade.sicreditest.domain.associate.model.AssociateModel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AssociateDTO {

    private final UUID id;
    private final String name;
    private final String cpf;

    public static AssociateDTO convertToDTO(AssociateModel associateModel) {

        return AssociateDTO.builder()
                .id(associateModel.getId())
                .name(associateModel.getName())
                .cpf(associateModel.getCpf())
                .build();
    }
}