package br.org.pessoal.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DepartamentoMercadoriaDTO {

    @JsonProperty("codigoDepartamento")
    private Integer codDepartamento;

    @JsonProperty("descricaoDepartamento")
    private String descDepartamento;

    @JsonProperty("flagDepartamento")
    private Character flagDepartamento;

}
