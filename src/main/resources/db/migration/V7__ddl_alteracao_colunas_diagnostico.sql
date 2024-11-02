ALTER TABLE diagnostico
    DROP COLUMN IF EXISTS descricao;

ALTER TABLE diagnostico
    ALTER COLUMN diagnostico TYPE VARCHAR(4000),
    ALTER COLUMN diagnostico DROP NOT NULL;