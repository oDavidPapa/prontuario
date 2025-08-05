DROP TABLE IF EXISTS  arquivo;
DROP TABLE IF EXISTS  resultado_exame;

CREATE TABLE IF NOT EXISTS arquivo (
    id int8 PRIMARY KEY DEFAULT nextval('seq_arquivo'),
    nome VARCHAR(255),
    arquivo BYTEA,
    descricao VARCHAR(255),
    tipo VARCHAR(100),
    id_consulta int8,
    CONSTRAINT fk_arquivo_consulta FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);