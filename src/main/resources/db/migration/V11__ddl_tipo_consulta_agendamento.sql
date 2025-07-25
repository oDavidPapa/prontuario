ALTER TABLE agenda_consulta
    ADD COLUMN IF NOT EXISTS tipo_consulta VARCHAR(100);

ALTER TABLE agenda
    RENAME COLUMN data TO data_agendamento;