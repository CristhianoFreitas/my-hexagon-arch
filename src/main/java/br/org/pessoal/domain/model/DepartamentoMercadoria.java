package br.org.pessoal.domain.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Representa o departamento da mercadoria.
 */
@Data
public class DepartamentoMercadoria {

    @NotNull
    private Integer codDepartamento;

    @NotEmpty
    private String descDepartamento;

    private Character flagDepartamento;

}
