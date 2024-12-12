package com.ufes.prontuario.dto.doenca;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.model.Cid;

public class CidConverter {

    public static Cid toEntity(CidCadastroDTO cidCadastroDTO) {

        var cid = new Cid();
        cid.setDescricao(cidCadastroDTO.getDescricao());
        cid.setCodigo(cidCadastroDTO.getCodigo());
        cid.setAuditoria(new Auditoria());
        return cid;
    }

    public static CidDTO toDTO(Cid cid) {
        return CidDTO.builder()
                .id(cid.getId())
                .descricao(cid.getDescricao())
                .codigo(cid.getCodigo())
                .build();
    }
}
