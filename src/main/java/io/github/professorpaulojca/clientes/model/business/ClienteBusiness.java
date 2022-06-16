package io.github.professorpaulojca.clientes.model.business;

import io.github.professorpaulojca.clientes.model.entity.Cliente;
import org.springframework.stereotype.Component;

@Component
public class ClienteBusiness {
    public void tratarCliente(Cliente cliente){
        cliente.setCpf(cliente.getCpf().replace(".","").replace("-",""));
    }
}
