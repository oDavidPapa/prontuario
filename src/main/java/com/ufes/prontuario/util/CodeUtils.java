package com.ufes.prontuario.util;

public class CodeUtils {

    public static String getDigtsOnly(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.replaceAll("\\D", "");
    }
}
