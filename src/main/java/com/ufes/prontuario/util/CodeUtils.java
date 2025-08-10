package com.ufes.prontuario.util;

import java.security.SecureRandom;

public class CodeUtils {
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String getDigtsOnly(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.replaceAll("\\D", "");
    }


    public static String gerarSenhaAlfanumerica(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(tamanho);
        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(CARACTERES.length());
            sb.append(CARACTERES.charAt(index));
        }
        return sb.toString();
    }
}
