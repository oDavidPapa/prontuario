CREATE SEQUENCE IF NOT EXISTS seq_contato;

CREATE TABLE IF NOT EXISTS contato (
    id int8 PRIMARY KEY,
    celular VARCHAR(20) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_usuario;

CREATE TABLE IF NOT EXISTS usuario (
    id int8 PRIMARY KEY,
    login VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_pessoa;

CREATE TABLE IF NOT EXISTS pessoa (
    id int8 PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    sexo CHAR(1) NOT NULL,
    data_nascimento DATE NOT NULL,
    id_contato int8,
    id_usuario int8,
    FOREIGN KEY (id_contato) REFERENCES contato(id),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_paciente;

CREATE TABLE IF NOT EXISTS paciente (
    id int8 PRIMARY KEY,
    peso DECIMAL(5,2) NOT NULL,
    altura DECIMAL(5,2) NOT NULL,
    id_pessoa int8 NOT NULL,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_medico;

CREATE TABLE IF NOT EXISTS medico (
    id int8 PRIMARY KEY,
    crm VARCHAR(20) NOT NULL,
    especialidade VARCHAR(100) NOT NULL,
    id_pessoa int8,
    FOREIGN KEY (id_pessoa) REFERENCES pessoa(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_alergia_paciente;

CREATE TABLE IF NOT EXISTS alergia_paciente (
    id int8 PRIMARY KEY,
    descricao VARCHAR(255),
    id_paciente int8,
    FOREIGN KEY (id_paciente) REFERENCES paciente(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_agenda;

CREATE TABLE IF NOT EXISTS agenda (
    id int8 PRIMARY KEY,
    data DATE NOT NULL,
    descricao VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_agenda_consulta;

CREATE TABLE IF NOT EXISTS agenda_consulta (
    id int8 PRIMARY KEY,
    id_medico int8 NOT NULL,
    id_paciente int8 NOT NULL,
    id_agenda int8 NOT NULL,
    FOREIGN KEY (id_medico) REFERENCES medico(id),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_agenda) REFERENCES agenda(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_consulta;

CREATE TABLE IF NOT EXISTS consulta (
    id int8 PRIMARY KEY,
    motivo VARCHAR(255) NOT NULL,
    data DATE NOT NULL,
    tipo VARCHAR(50) NOT NULL,
    id_medico int8 NOT NULL,
    id_paciente int8 NOT NULL,
    id_agenda_consulta int8,
    id_consulta int8,
    FOREIGN KEY (id_medico) REFERENCES medico(id),
    FOREIGN KEY (id_paciente) REFERENCES paciente(id),
    FOREIGN KEY (id_agenda_consulta) REFERENCES agenda_consulta(id),
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_tratamento;

CREATE TABLE IF NOT EXISTS tratamento (
    id int8 PRIMARY KEY,
    tratamento VARCHAR(4000),
    descricao VARCHAR(255) NOT NULL,
    id_consulta int8 NOT NULL,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_medicamento;

CREATE TABLE IF NOT EXISTS medicamento (
    id int8 PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_prescricao;

CREATE TABLE IF NOT EXISTS prescricao (
    id int8 PRIMARY KEY,
    id_consulta int8 NOT NULL,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_prescricao_consulta_medicamento;

CREATE TABLE IF NOT EXISTS prescricao_consulta_medicamento (
    id int8 PRIMARY KEY,
    dosagem VARCHAR(50),
    instrucao_uso varchar(4000) NOT NULL,
    id_medicamento int8 NOT NULL,
    id_prescricao int8 NOT NULL,
    FOREIGN KEY (id_medicamento) REFERENCES medicamento(id),
    FOREIGN KEY (id_prescricao) REFERENCES prescricao(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_exame;

CREATE TABLE IF NOT EXISTS exame (
    id int8 PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    id_consulta int8 NOT NULL,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_arquivo;

CREATE TABLE IF NOT EXISTS arquivo (
    id int8 PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    arquivo BYTEA
);

CREATE SEQUENCE IF NOT EXISTS seq_resultado_exame;

CREATE TABLE IF NOT EXISTS resultado_exame (
    id int8 PRIMARY KEY,
    descricao VARCHAR(255),
    id_exame int8,
    id_arquivo int8,
    FOREIGN KEY (id_exame) REFERENCES exame(id),
    FOREIGN KEY (id_arquivo) REFERENCES arquivo(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_doenca;

CREATE TABLE IF NOT EXISTS doenca (
    id int8 PRIMARY KEY,
    cid VARCHAR(45),
    descricao VARCHAR(255) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS seq_diagnostico;

CREATE TABLE IF NOT EXISTS diagnostico (
    id INT8 PRIMARY KEY,
    diagnostico VARCHAR(4000) NOT NULL,
    descricao VARCHAR(255) NOT NULL,
    id_consulta int8 NOT NULL,
    FOREIGN KEY (id_consulta) REFERENCES consulta(id)
);

CREATE SEQUENCE IF NOT EXISTS seq_doenca_diagnostico;

CREATE TABLE IF NOT EXISTS doenca_diagnostico (
    id int8 PRIMARY KEY,
    id_doenca int8 NOT NULL,
    id_diagnostico int8 NOT NULL,
    FOREIGN KEY (id_doenca) REFERENCES doenca(id),
    FOREIGN KEY (id_diagnostico) REFERENCES diagnostico(id)
);