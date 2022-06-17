package io.github.professorpaulojca.clientes.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
    @NotNull
    @NotEmpty(message = "{campo.descricao.obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo.preco.obrigatorio}")
    private String preco;

    @Column(name = "data_cadastro", updatable = false) //nunca atualiza a coluna
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotEmpty(message = "{campo.data.obrigatorio}")
    private String data;

    @NotNull(message = "{campo.cliente.obrigatorio}")
    private Integer idCliente;
}
