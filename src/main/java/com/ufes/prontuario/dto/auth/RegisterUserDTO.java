package com.ufes.prontuario.dto.auth;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterUserDTO {

    @NonNull
    private String login;
    @NonNull
    private String senha;
    @NonNull
    private String role;
    @NonNull
    private Long idPessoa;
}
