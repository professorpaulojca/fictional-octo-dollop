package io.github.professorpaulojca.clientes.rest;

import io.github.professorpaulojca.clientes.model.entity.Usuario;
import io.github.professorpaulojca.clientes.model.repository.UsuarioRepository;
import io.github.professorpaulojca.clientes.rest.exception.UsuarioCadastradoException;
import io.github.professorpaulojca.clientes.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    //a partir do requiredeArgsConstructor todas as variaveis que são private final serão colocadas no construtor
    private final UsuarioService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void salvar(@RequestBody @Valid Usuario usuario){
        try{
            service.salvar(usuario);
        }catch(UsuarioCadastradoException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage() );
        }

    }
}
