package io.github.professorpaulojca.clientes.rest;

import io.github.professorpaulojca.clientes.model.business.ClienteBusiness;
import io.github.professorpaulojca.clientes.model.entity.Cliente;
import io.github.professorpaulojca.clientes.model.repository.ClienteRepository;
import io.github.professorpaulojca.clientes.util.Mensagem;
import io.github.professorpaulojca.clientes.util.Tipo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {

    private final ClienteRepository repository;
    private final ClienteBusiness business;
    private Mensagem msg;

    @Autowired
    public ClienteController(ClienteRepository repository, ClienteBusiness business){ //boa prática
        this.repository = repository;
        this.business = business;
    }

    @GetMapping
    public List<Cliente> obterTodos(){
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid Cliente cliente){
        business.tratarCliente(cliente);
        return repository.save(cliente);
     /*   repository.save(cliente);
        msg = Mensagem.builder().tipo(Tipo.builder().id(1).descricao("Success").build()).descricao("Cliente cadastrado com sucesso!").build();
        return msg;*/
    }

    @GetMapping("{codigo}")
    public Cliente acharPorId(@PathVariable("codigo") Integer id){ //pega o codigo da url e joga para id
        return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado."));
        //lança exception um expressão lambda
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mensagem deletar(@PathVariable Integer id){
        try {
            repository
                    .findById(id)
                    .map(cliente -> {
                        repository.delete(cliente);
                        return Void.TYPE;
                    });
            msg = Mensagem.builder().tipo(Tipo.builder().id(1).descricao("Success").build()).descricao("Cliente exluído com sucesso!").build();
        }catch (Exception ex){
            msg = Mensagem.builder().tipo(Tipo.builder().id(1).descricao("Error").build()).descricao("Houve um erro, verificar se o cliente está cadastrado.").build();
        }
        return msg;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Mensagem atualizar(@RequestBody @Valid Cliente clientealterado) {
        try {
            repository.save(clientealterado);
            msg = Mensagem.builder().tipo(Tipo.builder().id(1).descricao("Success").build()).descricao("Cliente atualizado com sucesso!").build();
        }catch (Exception ex){
            msg = Mensagem.builder().tipo(Tipo.builder().id(1).descricao("Error").build()).descricao("Houve um erro, verificar se o cliente foi atualizado.").build();
        }
        return msg;
    }
}
