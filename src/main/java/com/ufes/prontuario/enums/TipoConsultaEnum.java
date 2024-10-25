package com.ufes.prontuario.enums;

import lombok.Getter;

@Getter
public enum TipoConsultaEnum {
    RETORNO("Retorno"),
    EMERGENCIA("Emergencia"),
    ROTINA("Rotina"),
    PRIMEIRA_CONSULTA("Primeira Consulta");

    private final String descricao;

    TipoConsultaEnum(String descricao) {
        this.descricao = descricao;
    }
}
