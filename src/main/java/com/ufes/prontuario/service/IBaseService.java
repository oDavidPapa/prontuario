package com.ufes.prontuario.service;

public interface IBaseService<T, U, V> {

    T validarInsert(T dtoCadastro);
    T validarUpdate(T dtoCadastro, V id);
    void validarDelete(U entity);

    U prepareInsert(T dtoCadastro);
    U prepareUpdate(T dtoCadastro, V id);
}
