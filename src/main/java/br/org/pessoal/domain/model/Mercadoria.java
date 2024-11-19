package br.org.pessoal.domain.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Representa a mercadoria.
 */
@Data
public class Mercadoria {

    @NotNull
    private Integer id;

    @NotEmpty
    private String descMercadoria;

    private Integer codEspecie;

    private Integer codClasse;

    @Valid
    @NotNull
    private DepartamentoMercadoria departamento;
}