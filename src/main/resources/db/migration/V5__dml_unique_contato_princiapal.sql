CREATE UNIQUE INDEX idx_unico_contato_principal
    ON contato (id_pessoa, tipo_contato)
    WHERE tipo_contato = 'PRINCIPAL';
