ALTER TABLE consulta
    RENAME COLUMN data TO data_consulta;

ALTER TABLE consulta
    ALTER COLUMN data_consulta TYPE timestamp;