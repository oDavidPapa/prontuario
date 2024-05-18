package com.ufes.prontuario.service;

public interface IBaseService<T, U> {

    T validarInsert(T dtoCadastro);
    T validarUpdate(T dtoCadastro, Long id);
    void validarDelete(U entity);

    U prepareInsert(T dtoCadastro);
    U prepareUpdate(T dtoCadastro, Long id);

}
