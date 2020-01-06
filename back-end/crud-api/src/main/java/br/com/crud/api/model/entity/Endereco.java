package br.com.crud.api.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
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

    public Endereco() { }

    public Endereco(String cep, String logradouro, String bairro, String cidade, String uf, String complemento) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.complemento = complemento;
    }

}
