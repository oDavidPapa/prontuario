package com.ufes.prontuario.dto.arquivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ArquivoCadastroDTO {

    private String nome;
    private MultipartFile arquivo;
    private Long idConsulta;
    private String descricao;
}
