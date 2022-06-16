package io.github.professorpaulojca.clientes.util;

import lombok.*;
import org.springframework.stereotype.Component;

@Component
@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
    private String descricao;
    private Tipo tipo;
}
