ALTER TABLE consulta
    RENAME COLUMN motivo TO anamnese;

ALTER TABLE consulta
    ALTER COLUMN anamnese DROP NOT NULL;