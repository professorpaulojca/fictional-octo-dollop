package io.github.professorpaulojca.clientes.rest;

import io.github.professorpaulojca.clientes.model.entity.Cliente;
import io.github.professorpaulojca.clientes.model.entity.ServicoPrestado;
import io.github.professorpaulojca.clientes.model.repository.ClienteRepository;
import io.github.professorpaulojca.clientes.model.repository.ServicoPrestadoRepository;
import io.github.professorpaulojca.clientes.rest.dto.ServicoPrestadoDTO;
import io.github.professorpaulojca.clientes.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/servico-prestado")
@RequiredArgsConstructor
//não esquecer de configurar o CROSSORIGIN, neste projeto foi feito por filter
public class ServicoPrestadoController {

    public final ClienteRepository clienteRepository;
    public final ServicoPrestadoRepository repository;
    public final BigDecimalConverter bigDecimalConverter;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoPrestado salvar(@RequestBody @NotNull @Valid ServicoPrestadoDTO servicoPrestadoDTO){
        LocalDate data = LocalDate.parse(servicoPrestadoDTO.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Integer idCliente = servicoPrestadoDTO.getIdCliente();

        Cliente cliente = clienteRepository
                .findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Cliente inexistente"));

        ServicoPrestado servicoPrestado = new ServicoPrestado();
        servicoPrestado.setDescricao(servicoPrestadoDTO.getDescricao());
        servicoPrestado.setData(data);
        servicoPrestado.setCliente(cliente);
        servicoPrestado.setValor(bigDecimalConverter.converter(servicoPrestadoDTO.getPreco()));

        return repository.save(servicoPrestado);
    }

    @GetMapping
    public List<ServicoPrestado> pesquisar(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return repository.findByNomeAndMes(nome, mes);
    }
}
