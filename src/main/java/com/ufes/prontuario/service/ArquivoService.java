package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.arquivo.ArquivoCadastroDTO;
import com.ufes.prontuario.dto.arquivo.ArquivoConverter;
import com.ufes.prontuario.exception.ArquivoException;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.model.Arquivo;
import com.ufes.prontuario.repository.arquivo.ArquivoRepositoryCustom;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArquivoService implements IBaseService<ArquivoCadastroDTO, Arquivo> {

    private final ArquivoRepositoryCustom arquivoRepositoryCustom;
    private final ConsultaService consultaService;

    public Arquivo salvarArquivo(ArquivoCadastroDTO dto) throws IOException {
        return arquivoRepositoryCustom.insertAndReturnArquivo(
                dto.getNome(),
                dto.getArquivo().getBytes(),
                dto.getDescricao(),
                dto.getArquivo().getContentType(),
                dto.getIdConsulta()
        );
    }

    public Arquivo upload(MultipartFile arquivo, String nome, String descricao, Long idConsulta) {
        try {
            var arquivoCadatroDTO = ArquivoCadastroDTO.builder()
                    .arquivo(arquivo)
                    .nome(nome)
                    .descricao(descricao)
                    .idConsulta(idConsulta)
                    .build();
            return salvarArquivo(arquivoCadatroDTO);
        } catch (Exception e) {
            throw new ArquivoException(e.getMessage());
        }
    }

    public void deletarPorId(Long id) {
        if (!arquivoRepositoryCustom.deleteById(id)) {
            throw new RecursoNaoEncontradoException("Arquivo", id);
        }
    }

    public Arquivo findById(Long id) {
        return arquivoRepositoryCustom.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Arquivo", id));
    }

    public List<Arquivo> listarPorConsulta(Long idConsulta) {
        return arquivoRepositoryCustom.findByIdConsulta(idConsulta);
    }

    @Override
    public ArquivoCadastroDTO validarInsert(ArquivoCadastroDTO dtoCadastro) {
        return dtoCadastro;
    }

    @Override
    public ArquivoCadastroDTO validarUpdate(ArquivoCadastroDTO dtoCadastro, Long id) {
        return dtoCadastro;
    }

    @Override
    public void validarDelete(Arquivo entity) {

    }

    @Override
    public Arquivo prepareInsert(ArquivoCadastroDTO dtoCadastro) {
        try {
            var arquivo = ArquivoConverter.toEntity(dtoCadastro);
            arquivo.setConsulta(this.consultaService.findById(dtoCadastro.getIdConsulta()));
            return arquivo;
        } catch (IOException e) {
            throw new RecursoNaoEncontradoException("", dtoCadastro.getIdConsulta());
        }
    }

    @Override
    public Arquivo prepareUpdate(ArquivoCadastroDTO dtoCadastro, Long id) {
        return null;
    }
}
