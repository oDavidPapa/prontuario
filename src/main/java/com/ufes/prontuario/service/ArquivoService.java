package com.ufes.prontuario.service;

import com.ufes.prontuario.dto.arquivo.ArquivoCadastroDTO;
import com.ufes.prontuario.dto.arquivo.ArquivoConverter;
import com.ufes.prontuario.exception.RecursoNaoEncontradoException;
import com.ufes.prontuario.exception.UsuarioNaoAutenticadoException;
import com.ufes.prontuario.model.Arquivo;
import com.ufes.prontuario.repository.ArquivoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ArquivoService implements IBaseService<ArquivoCadastroDTO, Arquivo> {

    private final ArquivoRepository repository;
    private final ConsultaService consultaService;

    public Arquivo findById(Long id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Arquivo", id));
    }

    public List<Arquivo> listar() {
        return this.repository.findAll();
    }

    public Arquivo inserir(Arquivo arquivo, Long idConsulta) {
          var idArquivo = this.repository.insertArquivoNative(arquivo.getNome(),
                arquivo.getArquivo(),arquivo.getDescricao(), arquivo.getTipo(), idConsulta);

        return this.findById(idArquivo);
    }

    public Arquivo upload(MultipartFile arquivo, String nome, String descricao, Long idConsulta) {
        try {
            var arquivoCadatroDTO = ArquivoCadastroDTO.builder()
                    .arquivo(arquivo)
                    .nome(nome)
                    .descricao(descricao)
                    .idConsulta(idConsulta)
                    .build();
            var arquivoToSave = ArquivoConverter.toEntity(arquivoCadatroDTO);
            return this.inserir(arquivoToSave, idConsulta);
       } catch(Exception e) {
            throw new RecursoNaoEncontradoException("Arquivo", idConsulta);
       }
    }


    public Arquivo update(Long id, ArquivoCadastroDTO arquivoCadastroDTO) {
        return Optional.ofNullable(arquivoCadastroDTO)
                .map(aDto -> validarUpdate(aDto, id))
                .map(arquivo -> prepareUpdate(arquivo ,id))
                .map(this.repository::save)
                .orElseThrow();
    }

    public void delete(Long id) {
        var arquivo = this.findById(id);

        Optional.ofNullable(arquivo)
                .ifPresent(p -> {
                    this.validarDelete(p);
                    this.repository.delete(p);
                });
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
        return this.findById(id);
    }
}
