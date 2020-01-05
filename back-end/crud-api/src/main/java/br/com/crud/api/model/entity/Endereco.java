package br.com.crud.api.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Campo cep é obrigatório")
    private String cep;

    @NotNull(message = "O Campo logradouro é obrigatório")
    private String logradouro;

    @NotNull(message = "O Campo bairro é obrigatório")
    private String bairro;

    @NotNull(message = "O Campo cidade é obrigatório")
    private String cidade;

    @NotNull(message = "O Campo uf é obrigatório")
    private String uf;

    private String complemento;

}
