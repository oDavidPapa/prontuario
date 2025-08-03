ALTER TABLE prescricao
    ADD COLUMN IF NOT EXISTS descricao varchar(4000);

ALTER TABLE prescricao_consulta_medicamento
    ADD COLUMN IF NOT EXISTS medicamento varchar(255),
    ADD COLUMN IF NOT EXISTS observacao varchar(255);

ALTER TABLE prescricao_consulta_medicamento
    DROP COLUMN IF EXISTS dosagem,
    DROP COLUMN IF EXISTS id_medicamento;