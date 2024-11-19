package br.org.pessoal.adapter.in.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MercadoriaDTO {

    @JsonProperty("codigoSkuMercadoria")
    private Integer id;

    @JsonProperty("descricaoMercadoria")
    private String descMercadoria;

    @JsonProperty("codigoEspecie")
    private Integer codEspecie;

    @JsonProperty("codigoClasse")
    private Integer codClasse;

    @JsonProperty("departamento")
    private DepartamentoMercadoriaDTO departamento;

    @JsonProperty("vigencia")
    private VigenciaDTO vigencia;
}