ALTER TABLE cid
    ADD COLUMN id_diagnostico int8 NOT NULL,
    ADD CONSTRAINT fk_diagnostico_cid FOREIGN KEY (id_diagnostico) REFERENCES diagnostico(id);

ALTER TABLE cid
    ADD COLUMN created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN created_by VARCHAR(255) DEFAULT 'system',
    ADD COLUMN last_modified_date TIMESTAMP,
    ADD COLUMN last_modified_by VARCHAR(255);