package br.com.crud.api.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O Campo nome é obrigatório")
    @Size(min = 3, max = 100)
    private String nome;

    @NotNull(message = "O Campo cpf é obrigatório")
    private String cpf;

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @NotNull(message = "O endereço é obrigatório")
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    // @Valid
    // @NotNull(message = "Pelo menos um telefone é obrigatório")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefones;

    // @Valid
    // @NotNull(message = "Pelo menos um email é obrigatório")
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Email> emails;

    public Cliente() { }

    public Cliente(String nome, String cpf, Endereco endereco, List<Telefone> telefones, List<Email> emails) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.telefones = telefones;
        this.emails = emails;
    }

}
