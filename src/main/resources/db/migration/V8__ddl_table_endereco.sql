CREATE SEQUENCE IF NOT EXISTS seq_endereco;

CREATE TABLE IF NOT EXISTS endereco (
    id int8 PRIMARY KEY,
    logradouro VARCHAR(255) NOT NULL,
    numero VARCHAR(6),
    complemento VARCHAR(255),
    cep VARCHAR(20) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    pais VARCHAR(100) NOT NULL
);

ALTER TABLE endereco
    ADD COLUMN id_pessoa int8 NOT NULL,
    ADD CONSTRAINT fk_pessoa_endereco FOREIGN KEY (id_pessoa) REFERENCES pessoa(id);