package com.ufes.prontuario.controller;

import com.ufes.prontuario.config.security.JwtService;
import com.ufes.prontuario.dto.auth.AuthenticationConverter;
import com.ufes.prontuario.dto.auth.AuthenticationRequestDTO;
import com.ufes.prontuario.dto.auth.AuthenticationResponseDTO;
import com.ufes.prontuario.enums.Role;
import com.ufes.prontuario.model.Usuario;
import com.ufes.prontuario.repository.UsuarioRepository;
import com.ufes.prontuario.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/registrar")
    public ResponseEntity<AuthenticationResponseDTO> registrar(@RequestBody AuthenticationRequestDTO cadastro) {
        var usuario = this.usuarioService.salvar(AuthenticationConverter.authToDTOCadastro(cadastro));
        var token = jwtService.generateToken(usuario);

        return ResponseEntity.ok(AuthenticationResponseDTO
                .builder().token(token).build());
    }

    @PostMapping("/autenticar")
    public ResponseEntity<AuthenticationResponseDTO> autenticar(@RequestBody @Validated AuthenticationRequestDTO usuario) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(usuario.getLogin(), usuario.getSenha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = jwtService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(AuthenticationResponseDTO
                .builder().token(token).build());
    }


}
