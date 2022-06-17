package io.github.professorpaulojca.clientes.service;

import io.github.professorpaulojca.clientes.model.entity.Usuario;
import io.github.professorpaulojca.clientes.model.repository.UsuarioRepository;
import io.github.professorpaulojca.clientes.rest.exception.UsuarioCadastradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    public Usuario salvar(Usuario usuario){

       boolean exists = repository.existsByUsername(usuario.getUsername());

       if (exists) {
            throw new UsuarioCadastradoException(usuario.getUsername());
       } else {
           return repository.save(usuario);
       }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository
                .findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login inexistente"));


        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER").build(); //como não temos o tipo do usuario deixei fixo
    }
}
