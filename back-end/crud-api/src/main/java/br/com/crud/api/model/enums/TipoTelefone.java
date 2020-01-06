package br.com.crud.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TipoTelefone {
    RESIDENCIAL("Residencial"),
    COMERCIAL("Comercial"),
    CELULAR("Celular");

    @Getter
    private final String descricao;
}
