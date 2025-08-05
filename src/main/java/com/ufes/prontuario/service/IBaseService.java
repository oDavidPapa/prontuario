package com.ufes.prontuario.service;

import java.io.IOException;

public interface IBaseService<T, U> {

    T validarInsert(T dtoCadastro);
    T validarUpdate(T dtoCadastro, Long id);
    void validarDelete(U entity);

    U prepareInsert(T dtoCadastro) throws IOException;
    U prepareUpdate(T dtoCadastro, Long id);

}
