package com.ufes.prontuario.controller;

import com.ufes.prontuario.config.security.jwt.JwtService;
import com.ufes.prontuario.dto.auth.*;
import com.ufes.prontuario.dto.usuario.UsuarioConverter;
import com.ufes.prontuario.dto.usuario.UsuarioDTO;
import com.ufes.prontuario.model.Usuario;
import com.ufes.prontuario.service.UsuarioService;
import com.ufes.prontuario.util.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/registrar")
    public ResponseEntity<AuthenticationResponseDTO> registrar(@RequestBody RegisterUserDTO cadastro) {
        var usuario = this.usuarioService.salvar(AuthenticationConverter.registerToDTOCadastro(cadastro));
        var token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(AuthenticationResponseDTO
                .builder()
                .token(token)
                .id(usuario.getId())
                .build());
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponseDTO> autenticar(@RequestBody @Validated AuthenticationRequestDTO usuario) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = jwtService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(AuthenticationResponseDTO
                .builder().token(token).build());
    }

    @PostMapping("/alterar-senha")
    public BaseResponse<Void> alterarSenha(@RequestBody AlteracaoSenhaDTO alteracaoSenhaDTO, Authentication auth) {
        String username = auth.getName();
        usuarioService.alterarSenha(alteracaoSenhaDTO.getCurrentPassword(), alteracaoSenhaDTO.getNewPassword(), username);
        return new BaseResponse<>(null);
    }

    @PostMapping("/recuperar-senha")
    public BaseResponse<Void> recuperarSenha(@RequestBody RecuperacaoSenhaDTO recuperacaoSenhaDTO) {
        usuarioService.recuperarSenha(recuperacaoSenhaDTO);
        return new BaseResponse<>(null);
    }

    @GetMapping
    public BaseResponse<UsuarioDTO> filter(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String login,
            @RequestParam(required = false) String nome,
            Pageable pageable) {

        var usuarios = this.usuarioService.filter(id, login, nome, pageable);

        return new BaseResponse<>(usuarios.getContent().stream()
                .map(UsuarioConverter::toDTO)
                .collect(Collectors.toList()),
                usuarios.getTotalElements());
    }

    @GetMapping("/pessoa/{idPessoa}")
    public BaseResponse<UsuarioDTO> findByPessoa(@PathVariable Long idPessoa) {
        var usuario = this.usuarioService.findByPessoa(idPessoa);

        return new BaseResponse<>(Optional.ofNullable(usuario)
                .map(UsuarioConverter::toDTO)
                .orElse(null));
    }

    @GetMapping("/{id}")
    public BaseResponse<UsuarioDTO> findById(@PathVariable Long id) {
        var usuario = this.usuarioService.findById(id);

        return new BaseResponse<>(Optional.ofNullable(usuario)
                .map(UsuarioConverter::toDTO)
                .map(usuarioService::completeDTO)
                .orElse(null));
    }

    @PutMapping("/{id}")
    public BaseResponse<UsuarioDTO> update(@PathVariable Long id, @RequestBody RegisterUserDTO cadastro) {
        var usuario = this.usuarioService.update(id, AuthenticationConverter.registerToDTOCadastro(cadastro));

        return new BaseResponse<>(Optional.ofNullable(usuario)
                .map(UsuarioConverter::toDTO)
                .map(usuarioService::completeDTO)
                .orElse(null));
    }

    @PatchMapping("/alterar-status/{id}")
    public BaseResponse<UsuarioDTO> inativarUsuario(@PathVariable Long id) {
        var usuario = this.usuarioService.alterarStatus(id);

        return new BaseResponse<>(Optional.ofNullable(usuario)
                .map(UsuarioConverter::toDTO)
                .orElse(null));
    }
}
