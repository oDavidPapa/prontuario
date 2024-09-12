package com.ufes.prontuario.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequestDTO {

    @NonNull
    @JsonProperty("login")
    private String login;
    @NonNull
    @JsonProperty("senha")
    private String senha;
}
