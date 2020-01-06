package br.com.crud.api.model.entity;


import br.com.crud.api.model.enums.TipoTelefone;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "telefone")
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "O Campo tipo telefone é obrigatório")
    private TipoTelefone tipoTelefone;

    @NotNull(message = "O Campo número é obrigatório")
    private String numero;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Telefone() { }

    public Telefone(TipoTelefone tipoTelefone, String numero) {
        this.tipoTelefone = tipoTelefone;
        this.numero = numero;
    }
}
