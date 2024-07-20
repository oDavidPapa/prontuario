ALTER TABLE consulta ALTER COLUMN id_paciente DROP NOT NULL;
ALTER TABLE consulta ALTER COLUMN motivo TYPE varchar(4000) USING motivo::varchar(4000);

ALTER TABLE consulta
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE tratamento
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE pessoa
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE paciente
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE medico
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE contato
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE exame
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE alergia_paciente
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE agenda_consulta
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE diagnostico
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE doenca_diagnostico
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE prescricao_consulta_medicamento
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);

ALTER TABLE resultado_exame
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);