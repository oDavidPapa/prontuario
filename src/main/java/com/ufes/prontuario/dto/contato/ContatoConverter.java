package com.ufes.prontuario.dto.contato;

import com.ufes.prontuario.config.security.auditoria.Auditoria;
import com.ufes.prontuario.enums.TipoContatoEnum;
import com.ufes.prontuario.model.Contato;
import org.apache.commons.lang3.StringUtils;

public class ContatoConverter {

    public static Contato toEntity(ContatoCadastroDTO contatoCadastroDTO) {

        var contato = new Contato();
        contato.setCelular(StringUtils.getDigits(contatoCadastroDTO.getCelular()));
        contato.setEmail(contatoCadastroDTO.getEmail());
        contato.setTelefone(StringUtils.getDigits(contatoCadastroDTO.getTelefone()));
        contato.setTipoContato(TipoContatoEnum.valueOf(contatoCadastroDTO.getTipoContato()));
        contato.setAuditoria(new Auditoria());
        
        return contato;
    }

    public static ContatoDTO toDTO(Contato contato) {
        return ContatoDTO.builder()
                .celular(contato.getCelular())
                .email(contato.getEmail())
                .telefone(contato.getTelefone())
                .idPessoa(contato.getPessoa().getId())
                .tipoContato(contato.getTipoContato().name())
                .build();
    }
}
